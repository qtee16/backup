<!Doctype html>
<html>
    <head>
        <link rel="stylesheet" href="../../../public/css/root.css" />
        <link rel="stylesheet" href="../../../public/css/header.css" />
        <link rel="stylesheet" href="../../../public/css/footer.css" />
        <link rel="stylesheet" href="../../../public/css/body.css" />
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <?php 
            require '../components/header.php';
        ?>
        <div class="body col-12">
            <div>
                <?php 
                    require '../../config/config.php';
                    echo ROOT;
                    echo URL;
                ?>
            </div>
        </div>
        <?php 
            require '../components/footer.php';
        ?>
    </body>
    <script>
        function linkToCart() {
            window.location.href = "../cart/cart.php";
        }
    </script>
</html>