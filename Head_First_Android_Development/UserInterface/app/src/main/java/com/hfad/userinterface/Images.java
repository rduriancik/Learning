package com.hfad.userinterface;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class Images extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
    }

    public void imageViewUse() {
        ImageView photo = (ImageView) findViewById(R.id.photo);
        int image = R.drawable.starbuzz_logo;
        String description = "This is the logo";
        photo.setImageResource(image);
        photo.setContentDescription(description);
    }
}
