<div class="info-body">
    <div class="body-header">
        <img src="../../../public/res/img/info/user.png" alt="">
        <div class="wrap-title">
            <h2>Hồ sơ của tôi</h2>
            <p>Quản lý thông tin hồ sơ để bảo mật tài khoản</p>
        </div>
    </div>
    <hr>
    <div class="wrap-content">
        <form action="">
            <div class="col-12">
                <div class="col-7 pl-6">
                    <div class="wrap-input">
                        <label for="">Họ tên:</label>
                        <input type="text"><br>
                    </div>
                    <div class="wrap-input">
                        <label for="">Điện thoại:</label>
                        <input type="text"><br>
                    </div>
                    <div class="wrap-input">
                        <label for="">Địa chỉ:</label>
                        <input type="text"><br>
                    </div>
                    <div class="wrap-input">
                        <label for="">Email:</label>
                        <input type="text"><br>
                    </div>
                    <div class="wrap-input">
                        <label for="">Giới tính:</label>
                        <input id="man" type="radio" name="gender">
                        <label for="man">Nam</label>
                        <input id="woman" type="radio" name="gender">
                        <label for="woman">Nữ</label>
                        <input id="other" type="radio" name="gender">
                        <label for="other">Khác</label><br>
                    </div>
                    <div class="wrap-input">
                        <label for="birth">Ngày sinh:</label>
                        <select id="day" name="birth">
                            <?php
                                for ($i = 1; $i <= 31; $i++) {
                                    echo '<option value="'.$i.'">'.$i.'</option>';
                                }
                            ?>
                        </select>
                        <select id="month" name="birth">
                            <?php
                                for ($i = 1; $i <= 12; $i++) {
                                    echo '<option value="'.$i.'">'.$i.'</option>';
                                }
                            ?>
                        </select>
                        <select id="year" name="birth">
                            <?php
                                $mydate = getdate();
                                $year = (int)"$mydate[year]";
                                for ($i = $year; $i >= 1800; $i--) {
                                    echo '<option value="'.$i.'">'.$i.'</option>';
                                }
                            ?>
                        </select>
                    </div>
                </div>
                <div class="col-5">
                    <div class="wrap-edit-avt">
                        <img id="output" src="../../../public/res/img/info/avt.png" alt="">
                        <input type="file"  accept="image/*" name="image" id="selectedFile"  onchange="loadFile(event)" style="display: none;">
                        <input type="button" value="Chọn ảnh" onclick="document.getElementById('selectedFile').click();" />
    
                        <script>
                        var loadFile = function(event) {
                            var image = document.getElementById('output');
                            image.src = URL.createObjectURL(event.target.files[0]);
                        };
                        </script>
                    </div>
                </div>
            </div>
            <div class="col-12" style="margin-top: 32px;">
                <input type="submit" value="Cập nhật">
            </div>
        </form>
    </div>
</div>