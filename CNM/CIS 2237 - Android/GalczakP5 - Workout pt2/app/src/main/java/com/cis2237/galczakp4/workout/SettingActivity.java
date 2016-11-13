/* Anthony Galczak - agalczak@cnm.edu - Program 5 Workout pt. 2 */
/* SettingActivity.java */

package com.cis2237.galczakp4.workout;

import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends PreferenceActivity {

    private static List<String> fragments = new ArrayList<String>();



    @Override
    public void onBuildHeaders(List<PreferenceActivity.Header> target){
        loadHeadersFromResource(R.xml.app_preferences, target);
        fragments.clear();
        for(PreferenceActivity.Header header:target){
            fragments.add(header.fragment);
        }

    }

    @Override
    protected boolean isValidFragment(String fragmentName){
        return fragments.contains(fragmentName);

    }


    static public class WorkoutProgressFragment1 extends PreferenceFragment {
        public WorkoutProgressFragment1() {
            // Required empty public constructor
        }
        @Override
        public void onCreate(Bundle saveInstanceState){
            super.onCreate(saveInstanceState);
            addPreferencesFromResource(R.xml.limb_loosener);
        }
        //String settings = getArguments().getString("header");
    }

    static public class WorkoutProgressFragment2 extends PreferenceFragment {
        public WorkoutProgressFragment2() {
            // Required empty public constructor
        }
        @Override
        public void onCreate(Bundle saveInstanceState){
            super.onCreate(saveInstanceState);
            addPreferencesFromResource(R.xml.core_agony);
        }
        //String settings = getArguments().getString("header");
    }

    static public class WorkoutProgressFragment3 extends PreferenceFragment {
        public WorkoutProgressFragment3() {
            // Required empty public constructor
        }
        @Override
        public void onCreate(Bundle saveInstanceState){
            super.onCreate(saveInstanceState);
            addPreferencesFromResource(R.xml.wimp_special);
        }
        //String settings = getArguments().getString("header");
    }

    static public class WorkoutProgressFragment4 extends PreferenceFragment {
        public WorkoutProgressFragment4() {
            // Required empty public constructor
        }
        @Override
        public void onCreate(Bundle saveInstanceState){
            super.onCreate(saveInstanceState);
            addPreferencesFromResource(R.xml.strength_and_length);
        }
        //String settings = getArguments().getString("header");
    }





}
