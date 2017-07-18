package com.example.robert.firebasechat.contactlist.ui;

import com.example.robert.firebasechat.models.User;

/**
 * Created by robert on 18.7.2017.
 */

public interface OnItemClickListener {
    void onItemClick(User user);

    void onItemLongClick(User user);
}
