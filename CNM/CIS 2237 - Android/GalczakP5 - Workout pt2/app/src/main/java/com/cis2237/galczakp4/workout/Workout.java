/* Anthony Galczak - agalczak@cnm.edu - Program 5 Workout pt. 2 */
/* Workout.java */

package com.cis2237.galczakp4.workout;

/**
 * Created by anthony on 10/17/2016.
 */
public class Workout {

    private String name;
    private String description;

    Workout(){

    }

    // Overloaded constructor
    Workout(String name, String desc){
        this.name = name;
        this.description = desc;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static Workout[] workouts(){
        Workout[] workouts = {
                new Workout("The Limb Loosener",
                        "5 Handstand Pushups\n10 One-legged squats\n15 Pull-ups"),
                new Workout("Core Agony",
                        "100 Pull-ups \n100 Push-ups \n100 Sit-ups \n100 Squats"),
                new Workout("The Wimp Special",
                        "5 Pull-Ups \n10 Push-Ups \n15 Squats"),
                new Workout("Strength and Length",
                        "500 meter run \n21 X 1.5 pood kettleball swing \n21 Pull-Ups")

        };

        return workouts;
    }


}
