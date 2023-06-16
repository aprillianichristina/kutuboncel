import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {
    private final DeliveryService deliveryService;

    @Autowired
    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping
    public ResponseEntity<Delivery> createDelivery(@RequestBody Delivery delivery) {
        Delivery createdDelivery = deliveryService.createDelivery(delivery);
        return new ResponseEntity<>(createdDelivery, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Delivery>> getAllDeliveries() {
        List<Delivery> deliveries = deliveryService.getAllDeliveries();
        return new ResponseEntity<>(deliveries, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Delivery> getDeliveryById(@PathVariable Long id) {
        Delivery delivery = deliveryService.getDeliveryById(id);
        if (delivery != null) {
            return new ResponseEntity<>(delivery, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/order/{orderNumber}")
    public ResponseEntity<List<Delivery>> getDeliveriesByOrderNumber(@PathVariable String orderNumber) {
        List<Delivery> deliveries = deliveryService.getDeliveriesByOrderNumber(orderNumber);
        return new ResponseEntity<>(deliveries, HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Delivery>> getDeliveriesByStatus(@PathVariable String status) {
        List<Delivery> deliveries = deliveryService.getDeliveriesByStatus(status);
        return new ResponseEntity<>(deliveries, HttpStatus.OK);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Delivery> updateDeliveryStatus(@PathVariable Long id, @RequestBody String status) {
        Delivery delivery = deliveryService.getDeliveryById(id);
        if (delivery != null) {
            delivery.setStatus(status);
            Delivery updatedDelivery = deliveryService.updateDelivery(delivery);
            return new ResponseEntity<>(updatedDelivery, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

