import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PurchaseControllerTest {

    @Mock
    private PurchaseService purchaseService;

    @InjectMocks
    private PurchaseController purchaseController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(purchaseController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetAllPurchases() throws Exception {
        Purchase purchase1 = new Purchase(1L, "Product 1", 10);
        Purchase purchase2 = new Purchase(2L, "Product 2", 5);
        List<Purchase> purchases = Arrays.asList(purchase1, purchase2);

        when(purchaseService.getAllPurchases()).thenReturn(purchases);

        mockMvc.perform(MockMvcRequestBuilders.get("/purchases"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(purchase1.getId()))
                .andExpect(jsonPath("$[0].productName").value(purchase1.getProductName()))
                .andExpect(jsonPath("$[0].quantity").value(purchase1.getQuantity()))
                .andExpect(jsonPath("$[1].id").value(purchase2.getId()))
                .andExpect(jsonPath("$[1].productName").value(purchase2.getProductName()))
                .andExpect(jsonPath("$[1].quantity").value(purchase2.getQuantity()));

        verify(purchaseService, times(1)).getAllPurchases();
        verifyNoMoreInteractions(purchaseService);
    }

    @Test
    public void testGetPurchaseById() throws Exception {
        long purchaseId = 1L;
        Purchase purchase = new Purchase(purchaseId, "Product 1", 10);

        when(purchaseService.getPurchaseById(purchaseId)).thenReturn(purchase);

        mockMvc.perform(MockMvcRequestBuilders.get("/purchases/{id}", purchaseId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(purchase.getId()))
                .andExpect(jsonPath("$.productName").value(purchase.getProductName()))
                .andExpect(jsonPath("$.quantity").value(purchase.getQuantity()));

        verify(purchaseService, times(1)).getPurchaseById(purchaseId);
        verifyNoMoreInteractions(purchaseService);
    }

    @Test
    public void testCreatePurchase() throws Exception {
        Purchase purchase = new Purchase(1L, "Product 1", 10);

        when(purchaseService.createPurchase(any(Purchase.class))).thenReturn(purchase);

        String requestBody = objectMapper.writeValueAsString(purchase);

        mockMvc.perform(MockMvcRequestBuilders.post("/purchases")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(purchase.getId()))
                .andExpect(jsonPath("$.productName").value(purchase.getProductName()))
                .andExpect(jsonPath("$.quantity").value(purchase.getQuantity()));

        verify(purchaseService, times(1)).createPurchase(any(Purchase.class));
        verifyNoMoreInteractions(purchaseService);
    }

    @Test
    public void testUpdatePurchase() throws Exception {
        long purchaseId = 1L;
        Purchase existingPurchase = new Purchase(purchaseId, "Product 1", 10);
        Purchase updatedPurchase = new Purchase(purchaseId, "Updated Product 1", 5);

        when(purchaseService.getPurchaseById(purchaseId)).thenReturn(existingPurchase);
        when(purchaseService.updatePurchase(any(Purchase.class))).thenReturn(updatedPurchase);

        String requestBody = objectMapper.writeValueAsString(updatedPurchase);

        mockMvc.perform(MockMvcRequestBuilders.put("/purchases/{id}", purchaseId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(updatedPurchase.getId()))
                .andExpect(jsonPath("$.productName").value(updatedPurchase.getProductName()))
                .andExpect(jsonPath("$.quantity").value(updatedPurchase.getQuantity()));

        verify(purchaseService, times(1)).getPurchaseById(purchaseId);
        verify(purchaseService, times(1)).updatePurchase(any(Purchase.class));
        verifyNoMoreInteractions(purchaseService);
    }

    @Test
    public void testDeletePurchase() throws Exception {
        long purchaseId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/purchases/{id}", purchaseId))
                .andExpect(status().isOk());

        verify(purchaseService, times(1)).deletePurchase(purchaseId);
        verifyNoMoreInteractions(purchaseService);
    }
}

