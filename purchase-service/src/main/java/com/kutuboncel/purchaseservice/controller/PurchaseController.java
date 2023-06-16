import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public ResponseEntity<Purchase> createPurchase(@RequestBody Purchase purchase) {
        boolean isValidPurchase = purchaseService.validatePurchase(purchase);
        if (isValidPurchase) {
            Purchase createdPurchase = purchaseService.createPurchase(purchase);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPurchase);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{purchaseId}")
    public ResponseEntity<Purchase> getPurchaseById(@PathVariable Long purchaseId) {
        Purchase purchase = purchaseService.getPurchaseById(purchaseId);
        if (purchase != null) {
            return ResponseEntity.ok(purchase);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Purchase>> getAllPurchases() {
        List<Purchase> purchases = purchaseService.getAllPurchases();
        return ResponseEntity.ok(purchases);
    }

    @PutMapping("/{purchaseId}")
    public ResponseEntity<Purchase> updatePurchase(@PathVariable Long purchaseId, @RequestBody Purchase purchase) {
        Purchase updatedPurchase = purchaseService.updatePurchase(purchaseId, purchase);
        if (updatedPurchase != null) {
            return ResponseEntity.ok(updatedPurchase);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{purchaseId}")
    public ResponseEntity<Void> deletePurchase(@PathVariable Long purchaseId) {
        boolean deleted = purchaseService.deletePurchase(purchaseId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

