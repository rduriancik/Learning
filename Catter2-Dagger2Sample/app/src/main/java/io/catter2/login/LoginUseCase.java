package io.catter2.login;

import android.util.Log;

/**
 * Created by robert on 14.9.2017.
 */

public class LoginUseCase {
    private static final String TAG = "LoginUseCase";

    public String login(String username, String password) {
        try {
            LoginService loginService = new LoginService();
            String token = loginService.attemptLogin(username, password);
            return token;
        } catch (Exception e) {
            Log.e(TAG, "Login failed", e);
            return null;
        }
    }
}
