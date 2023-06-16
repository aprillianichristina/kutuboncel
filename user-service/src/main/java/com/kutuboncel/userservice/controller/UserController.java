@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/{userId}/addresses")
    public Address addAddress(@PathVariable Long userId, @RequestBody AddressRequest request) {
        return userService.addAddress(userId, request);
    }

    @PutMapping("/{userId}/addresses/{addressId}")
    public Address updateAddress(@PathVariable Long userId, @PathVariable Long addressId, @RequestBody AddressRequest request) {
        return userService.updateAddress(userId, addressId, request);
    }

    @DeleteMapping("/{userId}/addresses/{addressId}")
    public void deleteAddress(@PathVariable Long userId, @PathVariable Long addressId) {
        userService.deleteAddress(userId, addressId);
    }

    @GetMapping("/{userId}/payment-methods")
    public List<PaymentMethod> getPaymentMethods(@PathVariable Long userId) {
        return userService.getPaymentMethods(userId);
    }

    @PostMapping("/{userId}/payment-methods")
    public PaymentMethod addPaymentMethod(@PathVariable Long userId, @RequestBody PaymentMethodRequest request) {
        return userService.addPaymentMethod(userId, request);
    }

    @PutMapping("/{userId}/payment-methods/{paymentMethodId}")
    public PaymentMethod updatePaymentMethod(@PathVariable Long userId, @PathVariable Long paymentMethodId, @RequestBody PaymentMethodRequest request) {
        return userService.updatePaymentMethod(userId, paymentMethodId, request);
    }

    @DeleteMapping("/{userId}/payment-methods/{paymentMethodId}")
    public void deletePaymentMethod(@PathVariable Long userId, @PathVariable Long paymentMethodId) {
        userService.deletePaymentMethod(userId, paymentMethodId);
    }
}

