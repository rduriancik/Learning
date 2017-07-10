package micromaster.galileo.edu.databaseexample.database;

import android.content.ContentValues;
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

    public void addContact(Contact contact) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.COLUMN_NAME, contact.getName());
        contentValues.put(DataBaseHelper.COLUMN_LAST_NAME, contact.getLastName());
        contentValues.put(DataBaseHelper.COLUMN_EMAIL, contact.getEmail());
        contentValues.put(DataBaseHelper.COLUMN_PHONE_NUMBER, contact.getPhoneNumber());

        if (database != null) {
            database.insert(DataBaseHelper.TABLE_CONTACTS, null, contentValues);
        } else {
            throw new IllegalStateException("Database is not opened");
        }
    }

    //TODO: Implement the necessary code to get all the contacts from contacts table
    public ArrayList<Contact> getAllContacts() {
        return new ArrayList<Contact>();
    }

}
