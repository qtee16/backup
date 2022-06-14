const titles = document.getElementsByClassName('order-status');

// set anmation 
let clicked = [true, false, false, false, false, false];
let preIndex = 0;
titles[0].style.color = '#401D83';
titles[0].style.borderBottom = '2px solid #401D83';
for (let index = 0; index < titles.length; index++) {
    const element = titles[index];

    element.onclick = function () {
        titles[preIndex].style.color = '#000000';
        titles[preIndex].style.borderBottom = 'none';
        clicked[index] = true;
        clicked[preIndex] = false;
        preIndex = index;
        element.style.color = '#401D83';
        element.style.borderBottom = '2px solid #401D83';
    }

    element.onmouseenter = function () {
        element.style.color = '#401D83';
        element.style.borderBottom = '2px solid #401D83';
    }

    element.onmouseleave = function () {
        if (!clicked[index]) {
            element.style.color = '#000000';
            element.style.borderBottom = 'none';
        }else{
            element.style.color = '#401D83';
            element.style.borderBottom = '2px solid #401D83';
        }
    }
}

// clicked on menu bar - responsive
let navOpened = false;
function toggleMobileMenu(menu) { 
    menu.classList.toggle('open');
    if (!navOpened) {
        navOpened = true;
        document.getElementById('body').style.height = "345px";
    }else{
        navOpened = false;
        document.getElementById('body').style.height = "auto";
    }
}

// change placeholder when choose category
const selectElement = document.getElementsByTagName('select');
const inputSearch = document.getElementById('search-order-status');
function getSelectChange() {
    inputSearch.setAttribute("placeholder", "Nhập " + selectElement[0].value.toLowerCase());
}