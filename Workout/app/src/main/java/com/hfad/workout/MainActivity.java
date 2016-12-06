package com.hfad.workout;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WorkoutDetailFragment frag = (WorkoutDetailFragment)
                getFragmentManager().findFragmentById(R.id.detail_fragment);

        frag.setWorkout(1);
    }
}
