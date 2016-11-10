package com.hfad.userinterface;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Views extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_views);
    }

    protected void textViewUse() {
        TextView textView = (TextView) findViewById(R.id.text_view);
        textView.setText("Some new text");
    }

    protected void editTextUse() {
        EditText editText = (EditText) findViewById(R.id.edit_text);
        String text = editText.getText().toString();
    }

    /**
     * Called when the button is clicked
     */
    public void onButtonClicked(View view) {
        // Do something in response to button click
    }

    /**
     * Called when the toggle button is clicked
     */
    public void onToggleButtonClicked(View view) {
        // Get the state of the toggle button
        boolean on = ((ToggleButton) view).isChecked();
        if (on) {
            // On
        } else {
            // Off
        }
    }

    /**
     * Called when the switch is clicked
     */
    public void onSwitchClicked(View view) {
        // Is the switch on?
        boolean on = ((Switch) view).isChecked();
        if (on) {
            // On
        } else {
            // Off
        }
    }

    protected void checkBoxUse() {
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox_milk);
        boolean checked = checkBox.isChecked();

        if (checked) {

        }
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        // Retrieve which checkbox was checked
        switch (view.getId()) {
            case R.id.checkbox_milk:
                if (checked) {
                    // Milky coffee
                } else
                    // Black coffee
                    break;
            case R.id.checkbox_sugar:
                if (checked) {
                    // Sweet
                } else
                    // Keep it bitter
                    break;
        }
    }

    protected void radioButtonsUse() {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        int id = radioGroup.getCheckedRadioButtonId();

        if (id == -1) {
            // No item selected
        } else {
            RadioButton radioButton = (RadioButton) findViewById(id);
        }
    }

    protected void onRadioButtonClicked(View view) {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        int id = radioGroup.getCheckedRadioButtonId();

        switch (id) {
            case R.id.radio_astronaut:
                // Astronaut
                break;
            case R.id.radio_caveman:
                // Caveman
                break;
        }
    }

    protected void spinnerUse() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String string = String.valueOf(spinner.getSelectedItem());
    }

    public void createToast() {
        CharSequence text = "Hello, I'm a Toast!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this, text, duration);
        toast.show();
    }
}
