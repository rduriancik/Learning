package edu.galileo.mvp;

import android.os.AsyncTask;

public class LoginModelImpl implements LoginModel {

    private OnLoginFinishedListener listener;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "test@galileo.edu:asdfasdf", "test2@galileo.edu:asdfasdf"
    };

    @Override
    public void login(String username, String password, OnLoginFinishedListener listener) {
        this.listener = listener;

        new UserLoginTask(username, password).execute((Void) null);
    }

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            // TODO: register the new account here.
            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success) {
                listener.onSuccess();
            } else {
                listener.onPasswordError();
            }
        }

        @Override
        protected void onCancelled() {
            listener.onCanceled();
        }
    }
}
