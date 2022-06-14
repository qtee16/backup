<!Doctype html>
<html>
    <head>
        <link rel="stylesheet" href="../../../public/css/root.css" />
        <link rel="stylesheet" href="../../../public/css/header.css" />
        <link rel="stylesheet" href="../../../public/css/footer.css" />
        <link rel="stylesheet" href="../../../public/css/body.css" />
        <link rel="stylesheet" href="../../../public/css/orders/order_header.css" />
        <link rel="stylesheet" href="../../../public/css/orders/order_search.css" />
        <link rel="stylesheet" href="../../../public/css/orders/order_detail.css" />
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <?php 
            require '../components/header.php';
        ?>
        <div class="body col-12" id="body">
            <?php 
                require 'order_header.php';
            ?>
        </div>
        <?php 
            require 'order_search.php';
        ?>
        <div class="order-detail-wrap col-10">
            <div class="order-detail-status">
                <img src="../../../public/res/img/orders/shipped.png" alt="" srcset="">
                <b class="ship-success">Giao hàng thành công</b>
            </div><div class="order-detail-product">
                <div class="product-image">
                    <img src="../../../public/res/img/orders/product1.jpg" alt="" srcset="">
                </div><div class="product-info">
                    <div class="info-detail product-title">Áo phông tay lỡ Hàn Quốc, áo phông màu trắng form...</div>
                    <div class="info-detail product-category">Phân loại hàng : M(30-50kg)</div>
                    <div class="info-detail product-quantity">x1</div>
                    
                </div><div class="total-price">
                    <span>100.000đ</span>
                </div>
            </div><div class="order-detail-bottom">
                <div class="total-pay">
                    <b>Tổng số tiền: </b>
                    <span><b>100.000đ</b></span>
                </div><div class="order-button order-success">
                    <button class="success-btn">Mua lại</button>
                </div>
            </div>
        </div>

        <div class="order-detail-wrap col-10">
            <div class="order-detail-status">
                <img src="../../../public/res/img/orders/shipped.png" alt="" srcset="">
                <b class="confirm-waiting">Chờ xác nhận</b>
            </div><div class="order-detail-product">
                <div class="product-image">
                    <img src="../../../public/res/img/orders/product1.jpg" alt="" srcset="">
                </div><div class="product-info">
                    <div class="info-detail product-title">Áo phông tay lỡ Hàn Quốc, áo phông màu trắng form...</div>
                    <div class="info-detail product-category">Phân loại hàng : M(30-50kg)</div>
                    <div class="info-detail product-quantity">x2</div>
                    
                </div><div class="total-price">
                    <span>200.000đ</span>
                </div>
            </div><div class="order-detail-bottom">
                <div class="total-pay">
                    <b>Tổng số tiền: </b>
                    <span><b>180.000đ</b></span>
                </div><div class="order-button order-cancel">
                    <button class="cancel-btn">Hủy đơn hàng</button>
                </div>
            </div>
        </div>
        <?php 
            require '../components/footer.php';
        ?>
        <script src="../../../public/js/order.js"></script>
    </body>
</html>