/* Anthony Galczak - agalczak@cnm.edu - Program 5 Workout pt. 2 */
/* DetailActivity.java */

package com.cis2237.galczakp4.workout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity  {

    public static final String EXTRA_WORKOUT_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        WorkoutDetailFragment workoutDetailFragment = (WorkoutDetailFragment)getSupportFragmentManager().findFragmentById(R.id.detail_frag);

        // Obtaining the workoutId from mainActivity
        long workoutId = (Long)getIntent().getExtras().get(EXTRA_WORKOUT_ID);

        // Using the WorkoutDetailFragment class to set the text fields
        workoutDetailFragment.setWorkoutId(workoutId);

    }
}
