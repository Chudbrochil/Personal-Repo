/* Anthony Galczak - agalczak@cnm.edu - Program 4 Workout */
/* MainActivity.java */

package com.cis2237.galczakp4.workout;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements WorkoutListFragment.WorkoutListListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void itemClicked(long id) {
        View fragmentContainer = findViewById(R.id.fragment_container);

        // Not null is tablet
        if (fragmentContainer != null){
            WorkoutDetailFragment workoutDetailFrag = new WorkoutDetailFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            workoutDetailFrag.setWorkoutId(id);
            ft.replace(R.id.fragment_container, workoutDetailFrag);
            ft.commit();
        }
        // Null is a phone
        else{
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
        }
    }
}
