package galczake2.cis2237.com.galczake2;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private Button btnClassical, btnElectronica;
    private MediaPlayer mpClassical, mpElectronica;
    private int playing;
    private int whatIsPlaying = 0;


    private static final String TAG = "MainActivity";

    public void pauseSound(MediaPlayer mp){
        playing = 0;
        if(mp.equals(mpClassical)){
            btnClassical.setText("Play Classical!");
            btnElectronica.setVisibility(View.VISIBLE);
            btnClassical.setVisibility(View.VISIBLE);
        }
        else{
            btnElectronica.setText("Play Electronica!");
            btnClassical.setVisibility(View.VISIBLE);
            btnElectronica.setVisibility(View.VISIBLE);
        }
        mp.pause();
    }

    public void playSound(MediaPlayer mp){
        playing = 1;

        if(mp.equals(mpClassical)){
            btnClassical.setText("Pause Classical!");
            btnClassical.setVisibility(View.VISIBLE);
            btnElectronica.setText("Play Electronica!");
            if(mpElectronica.isPlaying() == true){
                mpElectronica.pause();
            }
        }
        else{
            btnElectronica.setText("Pause Electronica!");
            btnElectronica.setVisibility(View.VISIBLE);
            btnClassical.setText("Play Classical!");
            if(mpClassical.isPlaying() == true){
                mpClassical.pause();
            }

        }

        mp.start();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "In Create method");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnClassical = (Button)findViewById(R.id.btnClassical);
        btnElectronica = (Button)findViewById(R.id.btnElectronica);

        btnClassical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                switch (playing) {
                    case 0:
                        playSound(mpClassical);
                        break;
                    case 1:
                        pauseSound(mpClassical);
                        break;
                }
            }
        });

        btnElectronica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                switch(playing) {
                    case 0:
                        playSound(mpElectronica);
                        break;
                    case 1:
                        pauseSound(mpElectronica);
                        break;
                }
            }

        });

        mpClassical = new MediaPlayer();
        mpElectronica = new MediaPlayer();

        mpClassical = MediaPlayer.create(this, R.raw.classical);
        mpElectronica = MediaPlayer.create(this, R.raw.electronica);

        playing = 0;

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        Log.i(TAG, "In Save Instance");
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        Log.i(TAG, "In Restore Instance");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onStart() {
        Log.i(TAG, "In Start method");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "In Resume method");
        System.out.println(whatIsPlaying);
        if (whatIsPlaying == 1){
            playSound(mpClassical);
        }
        else if (whatIsPlaying == 2){
            playSound(mpElectronica);
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "In Pause method");
        if (mpClassical.isPlaying() == true){
            pauseSound(mpClassical);
            whatIsPlaying = 1;
        }
        else if (mpElectronica.isPlaying() == true){
            pauseSound(mpElectronica);
            whatIsPlaying = 2;
        }
        else{
            whatIsPlaying = 0;
        }
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "In Stop method");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.i(TAG, "In Restart method");
        if (whatIsPlaying == 1){
            playSound(mpClassical);
        }
        else if (whatIsPlaying == 2){
            playSound(mpElectronica);
        }
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "In Destroy method");
        mpClassical.release();
        mpElectronica.release();
        super.onDestroy();
    }
}
