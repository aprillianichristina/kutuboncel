import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
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
}

