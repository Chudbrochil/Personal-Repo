/* Anthony Galczak - agalczak@cnm.edu - Program 5 Workout pt. 2 */
/* WorkoutListFragment.java */

package com.cis2237.galczakp4.workout;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutListFragment extends ListFragment {

    private WorkoutListListener workoutListener;


    static interface WorkoutListListener {
        public void itemClicked(long id);
    }

    public void onAttach(Context context){
        super.onAttach(context);
        try{
            workoutListener = (WorkoutListListener) context;
        }
        catch (ClassCastException e){
            throw new ClassCastException((getActivity().toString()) +
                    " must implement WorkoutListListener");
        }
    }

    // Setting the workoutId when a list item is clicked
    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        if(workoutListener != null){
            workoutListener.itemClicked(id);
        }
    }


    public WorkoutListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        int workoutArrayLen = Workout.workouts().length;

        String[] workoutNames = new String[workoutArrayLen];

        // Allocating each workout's name into an array of workout names
        for(int i = 0; i < workoutArrayLen; ++i){
            workoutNames[i] = Workout.workouts()[i].getName();
        }

        // Making a list of workout names and setting it to a listView/listFragment
        final ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, workoutNames);

        setListAdapter(listAdapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }


}
