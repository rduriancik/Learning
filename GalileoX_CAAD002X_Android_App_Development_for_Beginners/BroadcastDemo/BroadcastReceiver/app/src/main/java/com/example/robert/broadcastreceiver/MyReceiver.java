package com.example.robert.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by robert on 2.7.2017.
 */

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String broadcastMessage = intent.getStringExtra("broadcast");
        Toast.makeText(context, "Message: " + broadcastMessage, Toast.LENGTH_SHORT).show();
    }
}
