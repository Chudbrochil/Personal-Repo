/* Anthony Galczak - agalczak@cnm.edu - Program 5 Workout pt. 2 */
/* WorkoutDetailFragment.java */

package com.cis2237.galczakp4.workout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutDetailFragment extends Fragment {

    private long workoutId;

    public WorkoutDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(savedInstanceState != null){
            workoutId = savedInstanceState.getLong("WorkoutId");
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout_detail, container, false);
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putLong("EXTRA_WORKOUT_ID", workoutId);
    }

    public void setWorkoutId(long workoutId){
        this.workoutId = workoutId;
    }

    public void onStart(){
        super.onStart();


        View view = getView();

        // Setting the text fields via the workoutId that has been fed to this fragment
        if(view != null){
            TextView title = (TextView) view.findViewById(R.id.txtTitle);
            Workout workout = Workout.workouts()[(int) workoutId];
            title.setText(workout.getName());
            TextView description = (TextView)view.findViewById(R.id.txtDescription);
            description.setText(workout.getDescription());
        }


        DisplayProgressFragment progressFrag = new DisplayProgressFragment();

        progressFrag.setWorkoutId((int)workoutId);
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.replace(R.id.progress_container, progressFrag);
        ft.commit();

    }

}
