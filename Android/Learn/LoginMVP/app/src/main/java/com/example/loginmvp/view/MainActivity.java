package com.example.loginmvp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.loginmvp.R;
import com.example.loginmvp.model.User;
import com.example.loginmvp.presenter.LoginPresenter;
import com.example.loginmvp.presenter.LoginPresenterInterface;

public class MainActivity extends AppCompatActivity implements LoginPresenterInterface {

    private EditText edtEmail, edtPassword;
    private TextView txtNotification;
    private Button btnLogin;

    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        txtNotification = (TextView) findViewById(R.id.txtNotification);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        mLoginPresenter = new LoginPresenter(this);

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

        mLoginPresenter.login(user);
    }

    @Override
    public void loginSuccess() {
        txtNotification.setVisibility(View.VISIBLE);
        txtNotification.setText("Login successfully");
        txtNotification.setTextColor(getResources().getColor(R.color.teal_200));
    }

    @Override
    public void loginError() {
        txtNotification.setVisibility(View.VISIBLE);
        txtNotification.setText("Login failed");
        txtNotification.setTextColor(getResources().getColor(R.color.design_default_color_error));
    }
}