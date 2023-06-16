
function loadProductList() {

}


function loadProductDetails(productId) {

}

document.querySelectorAll('.product').forEach(function (product) {
  product.addEventListener('click', function (event) {
    event.preventDefault();
    var productId = product.dataset.productId;

  });
});


