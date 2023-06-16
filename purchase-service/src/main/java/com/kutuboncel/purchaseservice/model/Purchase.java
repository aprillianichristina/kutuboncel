import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
    private List<PurchasedProduct> purchasedProducts;

    @OneToOne(mappedBy = "purchase", cascade = CascadeType.ALL)
    private Delivery delivery;

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "payment_method")
    private String paymentMethod;

    public Purchase() {
    }

    public Purchase(List<PurchasedProduct> purchasedProducts, Delivery delivery, double totalAmount, String paymentMethod) {
        this.purchasedProducts = purchasedProducts;
        this.delivery = delivery;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PurchasedProduct> getPurchasedProducts() {
        return purchasedProducts;
    }

    public void setPurchasedProducts(List<PurchasedProduct> purchasedProducts) {
        this.purchasedProducts = purchasedProducts;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}

