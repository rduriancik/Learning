package com.example.robert.happybirthdayapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "MainActivity";

    private static final int READ_CONTACTS_PERMISSION_REQUEST = 1;
    private static final int CONTACT_LOADER_ID = 90;
    private static final int CONTACT_ID_INDEX = 0;
    private static final int LOOKUP_KEY_INDEX = 1;

    private SimpleCursorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        setupCursorAdapter();
        ListView lvContacts = (ListView) findViewById(R.id.lvContacts);
        lvContacts.setAdapter(mAdapter);
        lvContacts.setOnItemClickListener(this);

        getPermissionToReadUserContacts();
    }

    private void setupCursorAdapter() {
        String[] uiBindFrom = {
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.PHOTO_URI};

        int[] uiBindTo = {R.id.tvName, R.id.ivImage};

        mAdapter = new SimpleCursorAdapter(this,
                R.layout.contacts_list_item,
                null,
                uiBindFrom,
                uiBindTo,
                0);

    }

    private void getPermissionToReadUserContacts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, READ_CONTACTS_PERMISSION_REQUEST);
            } else {
                loadingContacts();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case READ_CONTACTS_PERMISSION_REQUEST:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    loadingContacts();
                } else {
                    Log.d(TAG, "Permission denied");
                }
                break;
        }
    }

    private void loadingContacts() {
        getSupportLoaderManager().initLoader(CONTACT_LOADER_ID, new Bundle(), contactsLoader);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private LoaderManager.LoaderCallbacks<Cursor> contactsLoader = new LoaderManager.LoaderCallbacks<Cursor>() {
        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            String[] projectionFields = {
                    ContactsContract.Contacts._ID,
                    ContactsContract.Contacts.DISPLAY_NAME,
                    ContactsContract.Contacts.PHOTO_URI
            };

            return new CursorLoader(MainActivity.this,
                    ContactsContract.Contacts.CONTENT_URI,
                    projectionFields,
                    null,
                    null,
                    null);
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            mAdapter.swapCursor(data);
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
            mAdapter.swapCursor(null);
        }
    };

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Cursor cursor = ((SimpleCursorAdapter) adapterView.getAdapter()).getCursor();
        cursor.moveToPosition(i);

        String contactName = cursor.getString(LOOKUP_KEY_INDEX);

        Uri contactUri = ContactsContract.Contacts.getLookupUri(
                cursor.getLong(CONTACT_ID_INDEX), contactName);

        String email = getEmail(contactUri);

        if (!email.isEmpty()) {
            sendEmail(email, contactName);
        }
    }

    private void sendEmail(String email, String contactName) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto",
                email,
                null
        ));

        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.main_email_subject));
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.main_email_body, contactName));

        startActivity(Intent.createChooser(intent, getString(R.string.main_email_choose)));
    }

    private String getEmail(Uri contactUri) {
        String email = "";
        String id = contactUri.getLastPathSegment();

        Cursor cursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Email.CONTACT_ID + "=?",
                new String[]{id},
                null
        );

        int emailIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA);

        if (cursor.moveToFirst()) {
            email = cursor.getString(emailIndex);
        }

        return email;
    }
}
