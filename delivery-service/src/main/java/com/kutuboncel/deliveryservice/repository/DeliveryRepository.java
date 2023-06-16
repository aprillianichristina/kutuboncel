import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> findByOrderNumber(String orderNumber);
    
    List<Delivery> findByStatus(String status);

    List<Delivery> findByRecipientName(String recipientName);
}

