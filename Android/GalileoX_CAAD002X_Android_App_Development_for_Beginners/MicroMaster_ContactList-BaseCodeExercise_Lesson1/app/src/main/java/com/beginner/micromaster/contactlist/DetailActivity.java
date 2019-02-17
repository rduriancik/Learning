package com.beginner.micromaster.contactlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView contactName = (TextView) findViewById(R.id.contact_name);
        TextView contactLastName = (TextView) findViewById(R.id.contact_last_name);
        TextView contactEmail = (TextView) findViewById(R.id.contact_email);
        TextView contactPhone = (TextView) findViewById(R.id.contact_phone);

        Contact contact = getIntent().getExtras().getParcelable(Contact.class.getSimpleName());

        contactName.setText(contact.getName());
        contactLastName.setText(contact.getLastName());
        contactEmail.setText(contact.getEmail());
        contactPhone.setText(String.format(Locale.getDefault(), "%d", contact.getPhoneNumber()));
    }
}
