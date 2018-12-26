package io.catter2.login_activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import io.catter2.R;
import io.catter2.di.SharedPrefFavoritesRepoModule;
import io.catter2.di.UserComponent;
import io.catter2.favorites_activity.FavoritesActivity;
import io.catter2.login.LoginUseCase;

/**
 * A login screen that offers login via username/password.
 */
public class LoginActivity extends AppCompatActivity {

    private AutoCompleteTextView usernameActv;
    private EditText passwordEt;
    private TextView errorTv;

    @Inject
    LoginUseCase loginUseCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginActivityComponent.initializeAndInject(this);

        usernameActv = (AutoCompleteTextView) findViewById(R.id.login_username_actv);
        passwordEt = (EditText) findViewById(R.id.login_password_et);
        passwordEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        errorTv = (TextView) findViewById(R.id.login_error_tv);

        Button usernameSignInButton = (Button) findViewById(R.id.login_sign_in_bt);
        usernameSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (UserComponent.get() != null) {
            UserComponent.get().close();
        }
    }

    private void attemptLogin() {
        errorTv.setVisibility(View.GONE);
        String username = usernameActv.getText().toString();
        String password = passwordEt.getText().toString();

        String token = loginUseCase.login(username, password);

        if (token != null) {
            UserComponent.initialize(new SharedPrefFavoritesRepoModule(token));
            FavoritesActivity.launch(this);
        } else {
            errorTv.setVisibility(View.VISIBLE);
        }
    }
}

