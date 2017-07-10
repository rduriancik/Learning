package micromaster.galileo.edu.databaseexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import micromaster.galileo.edu.databaseexample.database.DataBaseDAO;
import micromaster.galileo.edu.databaseexample.model.Contact;

public class AddNewContactActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText nameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText phoneNumberEditText;

    private DataBaseDAO mDataBaseDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);

        mDataBaseDAO = new DataBaseDAO(this);
        mDataBaseDAO.open();

        //Get EditText references
        nameEditText = (EditText) findViewById(R.id.input_name);
        lastNameEditText = (EditText) findViewById(R.id.input_lastName);
        emailEditText = (EditText) findViewById(R.id.input_email);
        phoneNumberEditText = (EditText) findViewById(R.id.input_phoneNumber);

        Button button = (Button) findViewById(R.id.button_create_contact);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String phoneNumber = phoneNumberEditText.getText().toString();

                createNewContact(name, lastName, email, phoneNumber);
            }
        });
    }

    private void createNewContact(String name, String lastName, String email, String phoneNumber) {
        Contact contact = new Contact(name, lastName, email, phoneNumber);
        mDataBaseDAO.addContact(contact);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDataBaseDAO.close();
    }
}
