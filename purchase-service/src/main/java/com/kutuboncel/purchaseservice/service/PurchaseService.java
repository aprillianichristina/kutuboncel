import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository, ProductRepository productRepository) {
        this.purchaseRepository = purchaseRepository;
        this.productRepository = productRepository;
    }

    public Purchase createPurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    public Purchase getPurchaseById(Long id) {
        return purchaseRepository.findById(id).orElse(null);
    }

    public List<Purchase> getPurchasesByCustomerId(Long customerId) {
        return purchaseRepository.findByCustomerId(customerId);
    }

    public List<Purchase> getPurchasesByPaymentMethod(String paymentMethod) {
        return purchaseRepository.findByPaymentMethod(paymentMethod);
    }

    public double calculateTotalPurchaseAmount(Purchase purchase) {
        List<PurchasedProduct> purchasedProducts = purchase.getPurchasedProducts();
        double totalAmount = 0.0;

        for (PurchasedProduct purchasedProduct : purchasedProducts) {
            double productPrice = purchasedProduct.getProduct().getPrice();
            int quantity = purchasedProduct.getQuantity();
            totalAmount += productPrice * quantity;
        }

        return totalAmount;
    }

    public void deletePurchase(Long id) {
        purchaseRepository.deleteById(id);
    }

    public boolean validatePurchase(Purchase purchase) {
        List<PurchasedProduct> purchasedProducts = purchase.getPurchasedProducts();

        for (PurchasedProduct purchasedProduct : purchasedProducts) {
            Product product = purchasedProduct.getProduct();
            int requestedQuantity = purchasedProduct.getQuantity();

            if (product.getStock() < requestedQuantity) {
                return false;
            }
        }

        String paymentMethod = purchase.getPaymentMethod();

        if (!isValidPaymentMethod(paymentMethod)) {
            return false;
        }

        if (paymentMethod.equals("Credit Card")) {
            String creditCardNumber = purchase.getCreditCardNumber();
            if (!isValidCreditCardNumber(creditCardNumber)) {
                return false;
            }
        } else if (paymentMethod.equals("Bank Transfer")) {
            String bankAccountNumber = purchase.getBankAccountNumber();
            if (!isValidBankAccountNumber(bankAccountNumber)) {
                return false;
            }
        }

        return true;
    }

    private boolean isValidPaymentMethod(String paymentMethod) {
        return paymentMethod != null && (paymentMethod.equals("Credit Card") || paymentMethod.equals("Bank Transfer"));
    }

    private boolean isValidCreditCardNumber(String creditCardNumber) {
        if (creditCardNumber.length() != 16) {
            return false;
        }

        int sum = 0;
        boolean alternate = false;
        for (int i = creditCardNumber.length() - 1; i >= 0; i--) {
            int digit = Integer.parseInt(creditCardNumber.substring(i, i + 1));
            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
            alternate = !alternate;
        }
        return sum % 10 == 0;
    }

    private boolean isValidBankAccountNumber(String bankAccountNumber) {
        if (bankAccountNumber.length() != 10) {
            return false;
        }

        return bankAccountNumber.matches("\\d+");
    }
}

