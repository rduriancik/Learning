package com.hfad.workout;

/**
 * Created by robert on 6.12.2016.
 */

public class Workout {
    private String name;
    private String descripion;

    public static final Workout[] workouts = {
            new Workout("The Limb Loosener",
                    "5 Handstand push-ups\n10 1-legged squats\n15 Pull-ups"),
            new Workout("Core Agony",
                    "100 Pull-ups\n100 Push-ups\n100 Sit-ups\n100 Squats"),
            new Workout("The Wimp Special",
                    "5 Pull-ups\n10 Push-ups\n15 Squads"),
            new Workout("Strength and Length",
                    "500 meter run \n21 x 1.5 Pood kettleball swing\n21 x Pull-ups")
    };


    // Each workout has a name and description
    public Workout(String name, String descripion) {
        this.name = name;
        this.descripion = descripion;
    }

    public String getName() {
        return name;
    }

    public String getDescripion() {
        return descripion;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
