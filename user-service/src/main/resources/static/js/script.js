function submitForm() {
  var name = document.getElementById('name').value;
  var email = document.getElementById('email').value;
  var password = document.getElementById('password').value;

  if (name === '' || email === '' || password === '') {
    alert('Please fill in all fields');
    return;
  }

}

function submitAddressForm() {
  var street = document.getElementById('street').value;
  var city = document.getElementById('city').value;
  var state = document.getElementById('state').value;
  var zip = document.getElementById('zip').value;

  if (street === '' || city === '' || state === '' || zip === '') {
    alert('Please fill in all fields');
    return;
  }

}

function submitPaymentMethodForm() {

  var cardNumber = document.getElementById('cardNumber').value;
  var cardHolder = document.getElementById('cardHolder').value;
  var expirationDate = document.getElementById('expirationDate').value;
  var cvv = document.getElementById('cvv').value;

  if (cardNumber === '' || cardHolder === '' || expirationDate === '' || cvv === '') {
    alert('Please fill in all fields');
    return;
  }
}

document.getElementById('userForm').addEventListener('submit', function (event) {
  event.preventDefault();
  submitForm();
});

document.getElementById('addressForm').addEventListener('submit', function (event) {
  event.preventDefault();
  submitAddressForm();
});

document.getElementById('paymentMethodForm').addEventListener('submit', function (event) {
  event.preventDefault();
  submitPaymentMethodForm();
});

