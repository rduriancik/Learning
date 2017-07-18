package com.example.robert.firebasechat.login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.robert.firebasechat.ContactListActivity;
import com.example.robert.firebasechat.R;
import com.example.robert.firebasechat.login.LoginPresenter;
import com.example.robert.firebasechat.login.LoginPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.editTxtEmail)
    EditText editTxtEmail;
    @BindView(R.id.editTxtPassword)
    EditText editTxtPassword;
    @BindView(R.id.btnSignIn)
    Button btnSignIn;
    @BindView(R.id.btnSignUp)
    Button btnSignUp;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.layoutMainContainer)
    RelativeLayout layoutMainContainer;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginPresenter = new LoginPresenterImpl(this);
        loginPresenter.onCreate();
        loginPresenter.checkForAuthenticatedUser();
    }

    @Override
    protected void onDestroy() {
        loginPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void enableInputs() {
        setInput(true);
    }

    @Override
    public void disableInputs() {
        setInput(false);
    }

    private void setInput(boolean enabled) {
        editTxtEmail.setEnabled(enabled);
        editTxtPassword.setEnabled(enabled);
        btnSignIn.setEnabled(enabled);
        btnSignUp.setEnabled(enabled);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    @OnClick(R.id.btnSignUp)
    public void handleSignUp() {
        loginPresenter.registerNewUser(editTxtEmail.getText().toString(),
                editTxtPassword.getText().toString());
    }

    @Override
    @OnClick(R.id.btnSignIn)
    public void handleSignIn() {
        loginPresenter.validateLogin(editTxtEmail.getText().toString(),
                editTxtPassword.getText().toString());
    }

    @Override
    public void navigateToMainScreen() {
        startActivity(new Intent(this, ContactListActivity.class));
    }

    @Override
    public void loginError(String error) {
        editTxtPassword.setText("");
        String msgError = String.format(getString(R.string.login_error_message_signin), error);
        editTxtPassword.setError(msgError);
    }

    @Override
    public void newUserSuccess() {
        Snackbar.make(layoutMainContainer, R.string.login_notice_message_useradded, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void newUserError(String error) {
        editTxtPassword.setText("");
        String msgError = String.format(getString(R.string.login_error_message_signup), error);
        editTxtPassword.setError(msgError);
    }
}
