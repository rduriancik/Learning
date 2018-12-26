package micromaster.galileo.edu.databaseexample.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    public ArrayList<Contact> getAllContacts() {
        if (database != null) {
            ArrayList<Contact> contacts = new ArrayList<>();
            Cursor cursor = database.query(DataBaseHelper.TABLE_CONTACTS,
                    null, null, null, null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Contact contact = new Contact();
                contact.setName(cursor.getString(1));
                contact.setLastName(cursor.getString(2));
                contact.setEmail(cursor.getString(3));
                contact.setPhoneNumber(cursor.getString(4));

                contacts.add(contact);

                cursor.moveToNext();
            }

            cursor.close();

            return contacts;
        } else {
            throw new IllegalStateException("Database is not opened");
        }
    }

}
