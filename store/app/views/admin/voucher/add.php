<div class="body col-11">
    <h1 class="title">Thêm mã giảm giá</h1>
    <form action="">
        <div class="wrap-input">
            <label for="">Tên mã giảm giá:</label>
            <input type="text" placeholder="Nhập tên của mã giảm giá"><br>
        </div>
        <div class="wrap-input">
            <label for="">Loại:</label>
            <select name="" id="">
                <option value="">Giảm phần trăm</option>
                <option value="">Giảm tiền cố định</option>
            </select><br>
        </div>
        <div class="wrap-input">
            <label for="">Giá trị giảm:</label>
            <input type="text" placeholder="Nhập phần trăm giảm giá hoặc giá trị tiền giảm giá"><br>
        </div>
        <div class="wrap-input">
            <label for="">Giá trị đơn hàng tối thiểu:</label>
            <input type="text" placeholder="Giá trị phải là một số, tính theo đơn vị VNĐ"><br>
        </div>
        <div class="wrap-input">
            <label for="">Thời gian bắt đầu:</label>
            <select id="day" name="">
                <?php
                    for ($i = 1; $i <= 31; $i++) {
                        echo '<option value="'.$i.'">'.$i.'</option>';
                    }
                ?>
            </select>
            <select id="month" name="">
                <?php
                    for ($i = 1; $i <= 12; $i++) {
                        echo '<option value="'.$i.'">'.$i.'</option>';
                    }
                ?>
            </select>
            <select id="year" name="">
                <?php
                    $mydate = getdate();
                    $year = (int)"$mydate[year]";
                    for ($i = $year+1; $i >= 2000; $i--) {
                        echo '<option value="'.$i.'">'.$i.'</option>';
                    }
                ?>
            </select>
        </div>
        <div class="wrap-input">
            <label for="">Thời gian kết thúc:</label>
            <select id="day" name="">
                <?php
                    for ($i = 1; $i <= 31; $i++) {
                        echo '<option value="'.$i.'">'.$i.'</option>';
                    }
                ?>
            </select>
            <select id="month" name="">
                <?php
                    for ($i = 1; $i <= 12; $i++) {
                        echo '<option value="'.$i.'">'.$i.'</option>';
                    }
                ?>
            </select>
            <select id="year" name="">
                <?php
                    $mydate = getdate();
                    $year = (int)"$mydate[year]";
                    for ($i = $year+1; $i >= 2000; $i--) {
                        echo '<option value="'.$i.'">'.$i.'</option>';
                    }
                ?>
            </select>
        </div>
        <div class="wrap-input">
            <label for="">Số lượng:</label>
            <input type="text" placeholder="Số lượng mã giảm giá">
        </div>
        <div class="wrap-submit">
            <input type="submit" value="Xác nhận">
        </div>
    </form>
</div>