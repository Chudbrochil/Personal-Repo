/* Anthony Galczak - agalczak@cnm.edu - Program 5 Workout pt. 2 */
/* DisplayProgressFragment.java */

package com.cis2237.galczakp4.workout;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayProgressFragment extends Fragment {

    private long workoutId;
    Button progressButton;

    public DisplayProgressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);


        if(savedInstanceState != null){
            workoutId = savedInstanceState.getLong("workoutId", workoutId);
        }

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_display_progress, container, false);

        TextView workoutText = (TextView) view.findViewById(R.id.txtWorkouts);

        // Current activity detail view
        progressButton = (Button) view.findViewById(R.id.btnProgress);

        String display = "";

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());

        switch((int)workoutId){
            case 0:
                display += "\nHandstand pushups: " + sharedPref.getString("handstand-pushups", "none");
                display += "\nOne-Legged squats: " + sharedPref.getString("one-legged-squats", "none");
                display += "\nPull-ups: " + sharedPref.getString("pull-ups-1", "none");
                break;
            case 1:
                display += "\nPull-ups: " + sharedPref.getString("pull-ups-2", "none");
                display += "\nPush-ups: " + sharedPref.getString("push-ups-1", "none");
                display += "\nSit-ups: " + sharedPref.getString("sit-ups", "none");
                display += "\nSquats: " + sharedPref.getString("squats-1", "none");
                break;
            case 2:
                display += "\nPull-ups: " + sharedPref.getString("pull-ups-3", "none");
                display += "\nPush-ups: " + sharedPref.getString("push-ups-2", "none");
                display += "\nSquats: " + sharedPref.getString("squats-2", "none");
                break;
            case 3:
                display += "\nRun: " + sharedPref.getString("run", "none");
                display += "\nKettleball swing: " + sharedPref.getString("kettlebell", "none");
                display += "\nPull-Ups: " + sharedPref.getString("pull-ups-4", "none");
                break;
        }

        // Setting the display text into the progress fragment textview
        workoutText.setText(display);

        progressButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                intent.putExtra("workoutId", workoutId);
                startActivity(intent);
            }

        });


        return view;
    }

    public void setWorkoutId(int workoutId)
    {
        this.workoutId = workoutId;
    }

}
