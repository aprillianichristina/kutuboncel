import com.kutuboncel.product.controller.ProductController;
import com.kutuboncel.product.model.Product;
import com.kutuboncel.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testGetAllProducts() throws Exception {
        Product product1 = new Product(1L, "Product 1", 10000);
        Product product2 = new Product(2L, "Product 2", 20000);
        List<Product> productList = Arrays.asList(product1, product2);

        when(productService.getAllProducts()).thenReturn(productList);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Product 1")))
                .andExpect(jsonPath("$[0].price", is(10000)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Product 2")))
                .andExpect(jsonPath("$[1].price", is(20000)));
    }

    @Test
    public void testGetProductById() throws Exception {
        Product product = new Product(1L, "Product 1", 10000);

        when(productService.getProductById(anyLong())).thenReturn(product);

        mockMvc.perform(get("/products/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Product 1")))
                .andExpect(jsonPath("$.price", is(10000)));
    }

    @Test
    public void testCreateProduct() throws Exception {
        Product product = new Product(1L, "Product 1", 10000);
        String jsonProduct = "{\"name\":\"Product 1\",\"price\":10000}";

        when(productService.createProduct(product)).thenReturn(product);

        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonProduct))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Product 1")))
                .andExpect(jsonPath("$.price", is(10000)));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Product product = new Product(1L, "Product 1", 10000);
        String jsonProduct = "{\"name\":\"Product 1 Updated\",\"price\":150000}";

        when(productService.updateProduct(anyLong(), Mockito.any(Product.class))).thenReturn(product);

        mockMvc.perform(put("/products/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonProduct))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Product 1")))
                .andExpect(jsonPath("$.price", is(10000)));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        mockMvc.perform(delete("/products/{id}", 1))
                .andExpect(status().isNoContent());
    }
}

