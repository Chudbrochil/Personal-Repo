// Anthony Galczak - agalczak@cnm.edu
// CIS 2237 - Program 7 - Snake Game

package com.cis2237.galczakp7;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity {

    // Declarations of objects
    Canvas canvas;
    SnakeAnimView snakeAnimView;
    Bitmap headAnimBitmap;
    Bitmap bodyBitmap;
    Bitmap tailBitmap;
    Rect rectToBeDrawn;

    // Initializations of nums
    int screenHeight = 64;
    int screenWidth = 64;
    int numFrames = 6;
    int frameNumber;
    long lastFrameTime;
    int fps;
    int hiScore;
    int topGap;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Find out the width and height of the screen, set them to screenHeight and screenWidth
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        topGap = screenHeight / 14;

        // Drawing initial head, body, tail
        headAnimBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.head_sprite_sheet);
        bodyBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.body);
        bodyBitmap = Bitmap.createScaledBitmap(bodyBitmap, 200, 200, false);
        tailBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tail);
        tailBitmap = Bitmap.createScaledBitmap(tailBitmap, 200, 200, false);

        snakeAnimView = new SnakeAnimView(this);
        setContentView(snakeAnimView);
        intent = new Intent(this, GameActivity.class);
    }

    // Inner class - SnakeAnimView
    class SnakeAnimView extends SurfaceView implements Runnable {
        Thread ourThread = null;
        SurfaceHolder ourHolder;
        volatile boolean playingSnake;
        Paint paint;
        int frameWidth;
        int frameHeight;

        // Constructor
        public SnakeAnimView(Context context){
            super(context);
            ourHolder = getHolder();
            paint = new Paint();
            frameWidth = headAnimBitmap.getWidth() / numFrames;
            frameHeight = headAnimBitmap.getHeight();
        }

        @Override
        public void run(){
            while(playingSnake){
                update();
                draw();
                controlFPS();
            }
        }

        public void update() {
            // Which frame should we draw
            rectToBeDrawn = new Rect((frameNumber * frameWidth), 0, (frameNumber * frameWidth + frameWidth) - 1,
                    frameHeight);

            // Go to next frame
            frameNumber++;

            // Make sure that  the drawing frames exist
            if(frameNumber == numFrames){
                frameNumber = 0;
            }

        }

        public void draw(){
            if(ourHolder.getSurface().isValid()){
                canvas = ourHolder.lockCanvas();

                canvas.drawColor(Color.BLUE);
                paint.setColor(Color.GREEN);
                paint.setTextSize(topGap/2);
                canvas.drawText("Snake game!", 10, topGap - 6, paint);

                // Place head
                Rect destRect = new Rect(screenWidth/2, screenHeight/2, screenWidth/2+200, screenHeight/2+200);

                // Place initial body and tail based on where the head was drawn
                canvas.drawBitmap(headAnimBitmap, rectToBeDrawn, destRect, paint);

                canvas.drawBitmap(bodyBitmap, screenWidth/2-200, screenHeight/2, paint);
                canvas.drawBitmap(tailBitmap, screenWidth/2-400, screenHeight/2, paint);

                ourHolder.unlockCanvasAndPost(canvas);
            }
        }

        public void controlFPS(){
            long timeThisFrame = (System.currentTimeMillis() - lastFrameTime);
            long timeToSleep = 500 - timeThisFrame;
            if(timeThisFrame > 0){
                fps = (int)(1000/timeThisFrame);
            }

            if(timeToSleep > 0){
                try{
                    ourThread.sleep(timeToSleep);
                }
                catch (InterruptedException e){}
            }
            lastFrameTime = System.currentTimeMillis();
        }

        @Override
        public boolean onTouchEvent(MotionEvent motionEvent){
            startActivity(intent);
            finish();
            return true;
        }

        // Starting the game back up and setting the state variable to true
        public void resume() {
            playingSnake = true;
            ourThread = new Thread(this);
            ourThread.start();
        }

        // Pausing the game and setting the state variable to false
        public void pause() {
            playingSnake = false;
            try{
                ourThread.join();
            }
            catch (InterruptedException e){}
        }
    }


    // Activity resume
    public void onResume(){
        super.onResume();
        snakeAnimView.resume();
    }

    // Activity pause
    public void onPause(){
        super.onPause();
        snakeAnimView.pause();
    }

    // Activity stop
    public void onStop(){
        super.onStop();
        finish();
    }

    // When key code is pressed
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            snakeAnimView.pause();
            finish();
            return true;
        }
        return false;
    }


}
