// Category hover 
const listManagements = document.getElementsByClassName("list-managements");
for (let index = 0; index < listManagements.length; index++) {
    const element = listManagements[index];
    element.onmouseenter = function () {
        element.style.backgroundColor = "#5994c6";
    }
    element.onmouseleave = function () {
        element.style.backgroundColor = "#0a0f55";
    }
}

// list of orders hover
const orderType = document.getElementsByClassName("leftbar-small-title-wrap");
for (let index = 0; index < orderType.length; index++) {
    const element = orderType[index];
    element.onmouseenter = function () {
        element.style.backgroundColor = "#5994c6";
    }
    element.onmouseleave = function () {
        element.style.backgroundColor = "#0a0f55";
    }
}

// Show and hide list of orders
let isListOrderOpened = false;
const orderList = document.getElementById("list-order-wrap");
const orderExtendIcon = document.getElementById("order-extend-icon");
listManagements[1].onclick = function () {
    console.log("clicked");
    if (!isListOrderOpened) {
        isListOrderOpened = true;
        orderList.style.display = "block";
        orderExtendIcon.setAttribute("src", "../../../public/res/img/admin/down.png");
    }else{
        isListOrderOpened = false;
        orderList.style.display = "none";
        orderExtendIcon.setAttribute("src", "../../../public/res/img/admin/right.png");
    }
}

// Show and hide list of events
let isListEventOpened = false;
const eventList = document.getElementById("list-event-wrap");
const eventExtendIcon = document.getElementById("event-extend-icon");
listManagements[3].onclick = function () {
    console.log("clicked");
    if (!isListEventOpened) {
        isListEventOpened = true;
        eventList.style.display = "block";
        eventExtendIcon.setAttribute("src", "../../../public/res/img/admin/down.png");
    }else{
        isListEventOpened = false;
        eventList.style.display = "none";
        eventExtendIcon.setAttribute("src", "../../../public/res/img/admin/right.png");
    }
}

// Show and hide list of products
let isListProductOpened = false;
const productList = document.getElementById("list-product-wrap");
const productExtendIcon = document.getElementById("product-extend-icon");
listManagements[2].onclick = function () {
    console.log("clicked");
    if (!isListProductOpened) {
        isListProductOpened = true;
        productList.style.display = "block";
        productExtendIcon.setAttribute("src", "../../../public/res/img/admin/down.png");
    }else{
        isListProductOpened = false;
        productList.style.display = "none";
        productExtendIcon.setAttribute("src", "../../../public/res/img/admin/right.png");
    }
}

// Show and hide list of vouchers
let isListVoucherOpened = false;
const voucherList = document.getElementById("list-voucher-wrap");
const voucherExtendIcon = document.getElementById("voucher-extend-icon");
listManagements[4].onclick = function () {
    console.log("clicked");
    if (!isListVoucherOpened) {
        isListVoucherOpened = true;
        voucherList.style.display = "block";
        voucherExtendIcon.setAttribute("src", "../../../public/res/img/admin/down.png");
    }else{
        isListVoucherOpened = false;
        voucherList.style.display = "none";
        voucherExtendIcon.setAttribute("src", "../../../public/res/img/admin/right.png");
    }
}