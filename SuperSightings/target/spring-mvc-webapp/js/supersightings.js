function validateLocationForm() {
    
    
    if (document.forms["locationForm"]["name"].value == "")
    {
        alert("Please provide your name!");
        document.myForm.name.focus();
        return false;
    }

    return true;
}


//
////$(document).ready(function() {
//    loadNewsFeed();
//    
//    
//});
//
//function loadNewsFee() {
//    var newsFeed = $("#newsfeed");
//    
//    $.ajax({
//        type: "GET",
//        url: "http://localhost:8080/sightings",
//        success:
//    });
//}
//
//function loadProducts() {
//    var itemList = $("#item-list");
//
//    $.ajax({
//        type: "GET",
//        url: "http://localhost:8080/items",
//        success: function (itemArray) {
//            $.each(itemArray, function (index, item) {
//                var addItem = "<button id='product-button-" + item.id + "' class='col-md-4 product-button' type='button'>";
//                    addItem += "<a onclick='selectItem(" + item.id + ")'>";
//                    addItem += "<div class='align-left' id='product-id-" + item.id + "' value='" + item.id + "'>" + item.id + "</div>";
//                    addItem += "<div id='product-name-" + item.id + "'>" + item.name + "</div>";
//                    addItem += "<div id='product-price-" + item.id + "'>$" + item.price.toFixed(2) + "</div>";
//                    addItem += "<div id='product-quantity-" + item.id + "'>Quantity Left: " + item.quantity + "</div>";
//                    addItem += "</a>";
//                    addItem += "</button>";
//
//                itemList.append(addItem);
//            });
//        },
//        error: function () {
//            alert("Could not load product list from service");
//        }
//    });
//}