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

public class DeliveryControllerTest {

    @Mock
    private DeliveryService deliveryService;

    @InjectMocks
    private DeliveryController deliveryController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(deliveryController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetAllDeliveries() throws Exception {
        Delivery delivery1 = new Delivery(1L, "Address 1", "In Progress");
        Delivery delivery2 = new Delivery(2L, "Address 2", "Delivered");
        List<Delivery> deliveries = Arrays.asList(delivery1, delivery2);

        when(deliveryService.getAllDeliveries()).thenReturn(deliveries);

        mockMvc.perform(MockMvcRequestBuilders.get("/deliveries"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(delivery1.getId()))
                .andExpect(jsonPath("$[0].address").value(delivery1.getAddress()))
                .andExpect(jsonPath("$[0].status").value(delivery1.getStatus()))
                .andExpect(jsonPath("$[1].id").value(delivery2.getId()))
                .andExpect(jsonPath("$[1].address").value(delivery2.getAddress()))
                .andExpect(jsonPath("$[1].status").value(delivery2.getStatus()));

        verify(deliveryService, times(1)).getAllDeliveries();
        verifyNoMoreInteractions(deliveryService);
    }

    @Test
    public void testGetDeliveryById() throws Exception {
        long deliveryId = 1L;
        Delivery delivery = new Delivery(deliveryId, "Address 1", "In Progress");

        when(deliveryService.getDeliveryById(deliveryId)).thenReturn(delivery);

        mockMvc.perform(MockMvcRequestBuilders.get("/deliveries/{id}", deliveryId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(delivery.getId()))
                .andExpect(jsonPath("$.address").value(delivery.getAddress()))
                .andExpect(jsonPath("$.status").value(delivery.getStatus()));

        verify(deliveryService, times(1)).getDeliveryById(deliveryId);
        verifyNoMoreInteractions(deliveryService);
    }

    @Test
    public void testCreateDelivery() throws Exception {
        Delivery delivery = new Delivery(1L, "Address 1", "In Progress");

        when(deliveryService.createDelivery(any(Delivery.class))).thenReturn(delivery);

        String requestBody = objectMapper.writeValueAsString(delivery);

        mockMvc.perform(MockMvcRequestBuilders.post("/deliveries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(delivery.getId()))
                .andExpect(jsonPath("$.address").value(delivery.getAddress()))
                .andExpect(jsonPath("$.status").value(delivery.getStatus()));

        verify(deliveryService, times(1)).createDelivery(any(Delivery.class));
        verifyNoMoreInteractions(deliveryService);
    }

    @Test
    public void testUpdateDelivery() throws Exception {
        long deliveryId = 1L;
        Delivery existingDelivery = new Delivery(deliveryId, "Address 1", "In Progress");
        Delivery updatedDelivery = new Delivery(deliveryId, "Updated Address", "Delivered");

        when(deliveryService.getDeliveryById(deliveryId)).thenReturn(existingDelivery);
        when(deliveryService.updateDelivery(any(Delivery.class))).thenReturn(updatedDelivery);

        String requestBody = objectMapper.writeValueAsString(updatedDelivery);

        mockMvc.perform(MockMvcRequestBuilders.put("/deliveries/{id}", deliveryId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(updatedDelivery.getId()))
                .andExpect(jsonPath("$.address").value(updatedDelivery.getAddress()))
                .andExpect(jsonPath("$.status").value(updatedDelivery.getStatus()));

        verify(deliveryService, times(1)).getDeliveryById(deliveryId);
        verify(deliveryService, times(1)).updateDelivery(any(Delivery.class));
        verifyNoMoreInteractions(deliveryService);
    }

    @Test
    public void testDeleteDelivery() throws Exception {
        long deliveryId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/deliveries/{id}", deliveryId))
                .andExpect(status().isOk());

        verify(deliveryService, times(1)).deleteDelivery(deliveryId);
        verifyNoMoreInteractions(deliveryService);
    }
}

