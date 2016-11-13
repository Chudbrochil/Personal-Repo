/* Anthony Galczak - agalczak@cnm.edu - Program 5 Workout pt. 2 */
/* SplashActivity.java */

package com.cis2237.galczakp4.workout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

// https://romannurik.github.io/AndroidAssetStudio/icons-launcher.html for ic_launcher pictures

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}



