package com.example.robertduriancik.tasktimer;

import android.app.DialogFragment;
import android.os.Bundle;

/**
 * Created by robert on 11.3.2017.
 */

public class AppDialog extends DialogFragment {
    private static final String TAG = "AppDialog";

    /**
     * The dialogue's callback interface to notify of user selected results (deletion confirmed, etc.)
     */
    interface DialogEvents {
        void onPositiveDialogResult(int dialogId, Bundle args);

        void onNegativeDialogResult(int dialogId, Bundle args);

        void onDialogCancelled(int dialogId, Bundle args);
    }
}
