function makeToast({
    heading = "",
    title = "",
    type = "",
    duration = 3000
}) {
    const icons = {
        success: "fas fa-check-circle",
        danger: "fas fa-exclamation-circle"
    }
    const mainToast = document.getElementById("toast");
    if (mainToast) {
        const toast = document.createElement('div');

        const autoRemoveId = setTimeout(function() {
            mainToast.removeChild(toast);
        }, duration + 1000);

        toast.onclick = function(e) {
            if(e.target.closest(".toast__close")) {
                mainToast.removeChild(toast);
                clearTimeout(autoRemoveId);
            }
        }

        toast.classList.add('toast', `toast--${type}`);
        const delay = (duration / 1000).toFixed(2)
        toast.style.animation = `slideInRight ease 0.5s, fadeOut linear 1s ${delay}s forwards`;
        toast.innerHTML = 
        `<div class="toast__icon">
            <i class="${icons[type]}"></i>
        </div>
        <div class="toast__body">
            <h3 class="toast__heading">${heading}</h3>
            <p class="toast__title">${title}</p>
        </div>
        <div class="toast__close">
            <i class="fas fa-times"></i>
        </div>`;
        mainToast.appendChild(toast);
    }
}

function showSuccess() {
    toastItem = {
        heading: "Thành công!",
        title: "Bạn đã đăng ký thành công. Đăng nhập để sử dụng.",
        type: "success",
        duration: 5000
    };
    makeToast(toastItem)
}

function showError() {
    toastItem = {
        heading: "Thất bại!",
        title: "Đã xảy ra lỗi, vui lòng thử lại.",
        type: "danger",
        duration: 5000
    };
    makeToast(toastItem)
}


