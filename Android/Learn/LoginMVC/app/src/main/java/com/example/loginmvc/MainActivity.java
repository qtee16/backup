package com.example.loginmvc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText edtEmail, edtPassword;
    private TextView txtNotification;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        txtNotification = (TextView) findViewById(R.id.txtNotification);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLogin();
            }
        });
    }

    private void clickLogin() {

        String txtEmail = edtEmail.getText().toString().trim();
        String txtPassword = edtPassword.getText().toString().trim();

        User user = new User(txtEmail, txtPassword);

        txtNotification.setVisibility(View.VISIBLE);
        if (user.isValidEmail() && user.isValidPassword()) {
            txtNotification.setText("Login successfully");
            txtNotification.setTextColor(getResources().getColor(R.color.teal_200));
        } else {
            txtNotification.setText("Login failed");
            txtNotification.setTextColor(getResources().getColor(R.color.design_default_color_error));
        }
    }
}