package com.example.loginmvp.presenter;

import com.example.loginmvp.model.User;

public class LoginPresenter {

    private LoginPresenterInterface mLoginPresenterInterface;

    public LoginPresenter(LoginPresenterInterface mLoginPresenterInterface) {
        this.mLoginPresenterInterface = mLoginPresenterInterface;
    }

    public void login(User user) {
        if (user.isValidEmail() && user.isValidPassword()) {
            mLoginPresenterInterface.loginSuccess();
        } else {
            mLoginPresenterInterface.loginError();
        }
    }
}
