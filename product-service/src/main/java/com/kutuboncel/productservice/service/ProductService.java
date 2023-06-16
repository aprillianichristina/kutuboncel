import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        if (isProductCodeExists(product.getCode())) {
            throw new RuntimeException("Product with code " + product.getCode() + " already exists.");
        }
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        Optional<Product> existingProduct = getProductById(product.getId());
        if (!existingProduct.isPresent()) {
            throw new RuntimeException("Product with ID " + product.getId() + " does not exist.");
        }
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        Optional<Product> existingProduct = getProductById(id);
        if (!existingProduct.isPresent()) {
            throw new RuntimeException("Product with ID " + id + " does not exist.");
        }
        productRepository.deleteById(id);
    }

    private boolean isProductCodeExists(String code) {
        return productRepository.findByCode(code).isPresent();
    }
}

