import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public DeliveryService(DeliveryRepository deliveryRepository, RestTemplate restTemplate) {
        this.deliveryRepository = deliveryRepository;
        this.restTemplate = restTemplate;
    }

    public Delivery createDelivery(Delivery delivery) {
        if (isDeliveryDataValid(delivery)) {
            return deliveryRepository.save(delivery);
        }
        return null;
    }

    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    public Delivery getDeliveryById(Long id) {
        if (deliveryExists(id)) {
            return deliveryRepository.findById(id).orElse(null);
        }
        return null;
    }

    public List<Delivery> getDeliveriesByOrderNumber(String orderNumber) {
        return deliveryRepository.findByOrderNumber(orderNumber);
    }

    public List<Delivery> getDeliveriesByStatus(String status) {
        return deliveryRepository.findByStatus(status);
    }

    public List<Delivery> getDeliveriesByRecipientName(String recipientName) {
        return deliveryRepository.findByRecipientName(recipientName);
    }

    public void updateDelivery(Delivery delivery) {
        if (deliveryExists(delivery.getId())) {
            deliveryRepository.save(delivery);
        }
    }

    public void deleteDelivery(Long id) {
        if (deliveryExists(id)) {
            deliveryRepository.deleteById(id);
        }
    }

    private boolean isDeliveryDataValid(Delivery delivery) {
        return true;
    }

    private boolean deliveryExists(Long id) {
        return true;
    }

    public Delivery createDelivery(DeliveryRequest request) {
        String url = "http://localhost:8080/deliveries";
        return restTemplate.postForObject(url, request, Delivery.class);
    }

    public void updateDeliveryStatus(Long deliveryId, DeliveryStatus status) {
        String url = "http://localhost:8080/deliveries/" + deliveryId + "/status";
        restTemplate.put(url, status);
    }
}
