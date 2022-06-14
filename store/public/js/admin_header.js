let avatar = document.getElementById("header-img-wrap");
let info = document.getElementById("header-info-wrap")
let dropdown = document.getElementById("dropdown-content");

let isDisplayed = false;
let isClicked = false;

avatar.onmouseenter = showDropdown;
avatar.onmouseleave = hideDropdown;
avatar.onclick = clicked;

info.onmouseenter = showDropdown;
info.onmouseleave = hideDropdown;
info.onclick = clicked;

dropdown.onmouseenter = showDropdown;
dropdown.onmouseleave = hideDropdown;

function showDropdown(){
    isDisplayed = true;
    dropdown.style.display = "block";
}

function hideDropdown() {
    if (!isClicked) {
        isDisplayed = false;
        dropdown.style.display = "none";
    }
}

function clicked() {
    // console.log("clicked");
    if (!isDisplayed) {
        isClicked = true;
        showDropdown();
    }else{
        isClicked = false;
        hideDropdown();
    }
}

// clicked on menu bar - responsive
let navOpened = false;
function toggleMobileMenu(menu) { 
    menu.classList.toggle('open');
    if (!navOpened) {
        navOpened = true;
        document.getElementById('left-bar').style.display = "block";
    }else{
        navOpened = false;
        document.getElementById('left-bar').style.display = "none";
    }
}

window.addEventListener("resize", function(event) {
    // console.log(this.window.innerWidth);
    if (navOpened) {
        document.getElementById('left-bar').style.display = "block";
    }else{
        if (this.window.innerWidth >= 992) {
            navOpened = false;
            document.getElementById('left-bar').style.display = "block";
        }else{
            document.getElementById('left-bar').style.display = "none";
        }
    }
});