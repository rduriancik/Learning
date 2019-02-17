package com.beginner.micromaster.contactlist;

import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {

    private String name;
    private String lastName;
    private String email;
    private int phoneNumber;

    public Contact(String name, String lastName, String email, int phoneNumber) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "name:" + name + ", " +
            "lastName:" + lastName + ", " +
            "email:" + email + ", " +
            "phoneNumber:" + phoneNumber;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.lastName);
        dest.writeString(this.email);
        dest.writeInt(this.phoneNumber);
    }

    protected Contact(Parcel in) {
        this.name = in.readString();
        this.lastName = in.readString();
        this.email = in.readString();
        this.phoneNumber = in.readInt();
    }

    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
