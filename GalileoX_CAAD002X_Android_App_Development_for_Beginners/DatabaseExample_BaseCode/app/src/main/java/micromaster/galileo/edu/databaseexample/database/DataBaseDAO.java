package micromaster.galileo.edu.databaseexample.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import micromaster.galileo.edu.databaseexample.model.Contact;

/**
 * Created by Byron on 3/26/2017.
 */

public class DataBaseDAO {

    private DataBaseHelper dataBaseHelper;
    private SQLiteDatabase database;

    public DataBaseDAO(Context context) {
        dataBaseHelper = new DataBaseHelper(context);
    }

    public void open() throws SQLException {
        database = dataBaseHelper.getWritableDatabase();
    }

    public void close() {
        dataBaseHelper.close();
    }

    //TODO: Implement the necessary code to add a contact instance to database
    public void addContact(Contact contact) {
    }

    //TODO: Implement the necessary code to get all the contacts from contacts table
    public ArrayList<Contact> getAllContacts() {
        return new ArrayList<Contact>();
    }

}
