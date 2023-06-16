import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> findByCustomerId(Long customerId);

    List<Purchase> findByPaymentMethod(String paymentMethod);

    List<Purchase> findByTotalAmountGreaterThan(double amount);

    List<Purchase> findByTotalAmountLessThan(double amount);

    List<Purchase> findByTotalAmountBetween(double minAmount, double maxAmount);

    List<Purchase> findByPurchaseDateBetween(LocalDate startDate, LocalDate endDate);

    List<Purchase> findByProductCategory(String category);

    List<Purchase> findByProductQuantityGreaterThan(int quantity);

    List<Purchase> findByProductPriceLessThan(double price);

    List<Purchase> findByShippingAddress(String address);

    List<Purchase> findByShippingStatus(String status);

    List<Purchase> findByShippingDateBetween(LocalDate startDate, LocalDate endDate);

}

