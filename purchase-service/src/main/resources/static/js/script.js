function loadPurchaseList() {

}

function loadPurchaseDetails(purchaseId) {

}


document.querySelectorAll('.purchase').forEach(function (purchase) {
  purchase.addEventListener('click', function (event) {
    event.preventDefault();
    var purchaseId = purchase.dataset.purchaseId;
 
  });
});


