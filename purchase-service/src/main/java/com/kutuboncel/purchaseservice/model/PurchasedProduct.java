import javax.persistence.*;

@Entity
@Table(name = "purchased_product")
public class PurchasedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    @OneToOne(mappedBy = "purchasedProduct", cascade = CascadeType.ALL)
    private Delivery delivery;

    public PurchasedProduct() {
    }

    public PurchasedProduct(Purchase purchase, Product product, int quantity, Delivery delivery) {
        this.purchase = purchase;
        this.product = product;
        this.quantity = quantity;
        this.delivery = delivery;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
}

