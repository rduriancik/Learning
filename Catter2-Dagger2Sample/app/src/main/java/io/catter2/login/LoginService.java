package io.catter2.login;

/**
 * Created by robert on 14.9.2017.
 */

public class LoginService {
    /**
     * A dummy authentication store containing known user names and passwords.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "me:123:token9", "yo:hunter2:token2"
    };

    public String attemptLogin(String username, String password) {
        for (String credential : DUMMY_CREDENTIALS) {
            String[] split = credential.split(":");
            if (username.equals(split[0]) && password.equals(split[1])) {
                return split[2];
            }
        }

        throw new UsernamePasswordDoesNotMatchException();
    }

    public class UsernamePasswordDoesNotMatchException extends RuntimeException {
        public UsernamePasswordDoesNotMatchException() {
            super("Username and password doesn't match");
        }
    }
}
