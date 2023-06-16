import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);

    List<Product> findByCategoryAndPriceLessThan(String category, double price);

    List<Product> findByNameContainingIgnoreCase(String keyword);

    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    List<Product> findByStockGreaterThanOrderByPriceAsc(int stock);

    Optional<Product> findFirstByCategoryOrderByPriceDesc(String category);

    List<Product> findByShippingAddress(String address);

    List<Product> findByShippingStatus(String status);

    List<Product> findByShippingDateBetween(LocalDate startDate, LocalDate endDate);

}

