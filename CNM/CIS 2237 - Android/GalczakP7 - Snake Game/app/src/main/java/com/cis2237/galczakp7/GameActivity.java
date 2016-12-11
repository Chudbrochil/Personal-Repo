// Anthony Galczak - agalczak@cnm.edu
// CIS 2237 - Program 7 - Snake Game

package com.cis2237.galczakp7;

import android.annotation.TargetApi;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.media.AudioManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


/**
 * Created by anthony on 12/4/2016.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    Bitmap headAnimBitmap;
    Rect rectToBeDrawn;


    private Canvas canvas;
    private SnakeView snakeView;

    //Bitmap objects for head, body, tail and apple
    private Bitmap headAnimationBitmap, bodyBitmap, tailBitmap, appleBitmap, flowerBitmap, headBitmap;

    //SoundPool and other sound related variables
    private SoundPool soundPool;
    private SoundPool.Builder soundPoolBuilder;

    private AudioAttributes attribute;
    private AudioAttributes.Builder attributeBuilder;

    private int sound1, sound2, sound3, sound4;

    //int travelDirection 0=up, 1=right, 2=down, 3=left
    private int travelDirection;

    //frame and screen variables
    private Rect rectangleToBeDrawn;
    private Rect flowerRectToBeDrawn;
    private int frameHeight = 64;
    private int frameWidth = 64;
    private int numFrames = 6;
    private int frameNumber;
    private int screenWidth, screenHeight, topGap;
    private long lastFrameTime;
    private int fps;
    private int score, hiScore;
    private int flowerNumFrames;
    private int flowerFrameNumber;

    private int blockSize, numBlocksHigh, numBlocksWide;
    private int[] snakeX, snakeY, snakeHead;
    private int snakeLength;

    private int appleX, appleY;

    //matrix objects
    private Matrix matrix90 = new Matrix();
    private Matrix matrix180 = new Matrix();
    private Matrix matrix270 = new Matrix();
    private Matrix matrixHeadFlip = new Matrix();

    Thread ourThread = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadSound();
        configureDisplay();

        snakeView = new SnakeView(this);
        setContentView(snakeView);
    }

    class SnakeView extends SurfaceView implements Runnable {

        Thread thread = null;
        SurfaceHolder ourHolder;
        volatile boolean playingSnake;
        Paint paint;

        public SnakeView(Context context){
            super(context);

            ourHolder = getHolder();
            paint = new Paint();
            snakeX = new int[200];
            snakeY = new int[200];
            snakeHead = new int[200];
            getSnake();
            getApple();
        }

        public void getSnake(){
            snakeLength = 3;

            //put the snake head in the middle of the screen and then attach the body and the tail
            //head
            snakeX[0] = numBlocksWide/2;
            snakeY[0] = numBlocksHigh/2;
            //snakeHead[0] = 0;

            //body
            snakeX[1] = snakeX[0]-1;
            snakeY[1] = snakeY[0];
            //snakeHead[1] = snakeHead[0];

            //tail
            snakeX[2] = snakeX[1]-1;
            snakeY[2] = snakeY[1];

            travelDirection = 1;
        }

        public void getApple(){
            //randomly places the apple on the screen
            Random random = new Random();
            appleX = random.nextInt(numBlocksWide-1)+1;
            // Bottom bar needs to be blocked more so apple isn't spawned off screen
            appleY = random.nextInt(numBlocksHigh-3)+1;
        }

        @Override
        public void run(){
            while(playingSnake){
                updateGame();
                drawGame();
                controlFPS();
            }
        }

        public void updateGame(){

            if(snakeX[0] == appleX && snakeY[0] == appleY){
                snakeLength++;

                getApple();
                score = score + snakeLength;

                if(score > hiScore){
                    hiScore = score;
                }

                soundPool.play(sound2, 0.8f, 0.8f, 0, 0, 1);

            }

            // Move the body from the back...
            for(int i = snakeLength; i > 0; i--)
            {
                snakeX[i] = snakeX[i-1];
                snakeY[i] = snakeY[i-1];
                snakeHead[i] = snakeHead[i-1];
            }

            switch(travelDirection){
                case 0:
                    // Move head up
                    snakeY[0]--;
                    snakeHead[0] = 0;
                    break;
                case 1:

                    // Move right
                    snakeX[0]++;
                    snakeHead[0] = 1;
                    break;
                case 2:
                    // Move down
                    snakeY[0]++;
                    snakeHead[0] = 2;
                    break;
                case 3:
                    // Move left
                    snakeX[0]--;
                    snakeHead[0] = 3;
                    break;
            }

            // Initial variable for whether the snake has died
            boolean dead = false;

            // Hitting a wall
            if(snakeX[0] == -1)
            {
                dead = true;
            }
            if(snakeX[0] >= numBlocksWide)
            {
                dead = true;
            }
            if(snakeY[0] == -1)
            {
                dead = true;
            }
            if(snakeY[0] == numBlocksHigh)
            {
                dead = true;
            }

            for(int i = snakeLength -1; i > 0; --i)
            {
                if((i > 4) && (snakeX[0] == snakeX[i]) && (snakeY[0] == snakeY[i]))
                {
                    dead = true;
                }
            }

            // Start the game again if the snake died
            if(dead == true)
            {
                soundPool.play(sound3, 0.9f, 0.9f, 0, 0, 1);
                score = 0;
                getSnake();
                getApple();
            }

        }

        public void drawGame(){
            if (ourHolder.getSurface().isValid()){
                canvas = ourHolder.lockCanvas();

                //Set colors and text and paint
                canvas.drawColor(Color.BLUE);
                canvas.drawText("Score: " + score + " - High score: " + hiScore, 10, topGap - 6, paint);

                paint.setColor(Color.GREEN);
                paint.setTextSize(topGap/2);


                //border
                paint.setStrokeWidth(3);
                //draw 4 lines

                //draw the snake
                Bitmap rotatedBitmap;
                Bitmap rotatedTailBitmap;

                rotatedBitmap = headBitmap;
                switch (snakeHead[0]){
                    case 0://Up
                        rotatedBitmap = Bitmap.createBitmap(rotatedBitmap,0,0,rotatedBitmap.getWidth(),rotatedBitmap.getHeight(),matrix270,true);
                        break;
                    case 1://right
                        // No rotation
                        break;
                    case 2://down 90
                        rotatedBitmap = Bitmap.createBitmap(rotatedBitmap,0,0,rotatedBitmap.getWidth(),rotatedBitmap.getHeight(),matrix90,true);
                        break;
                    case 3://left matrix flipHead
                        rotatedBitmap = Bitmap.createBitmap(rotatedBitmap,0,0,rotatedBitmap.getWidth(),rotatedBitmap.getHeight(),matrixHeadFlip,true);
                        break;
                }

                canvas.drawBitmap(rotatedBitmap,snakeX[0] * blockSize,(snakeY[0] * blockSize) + topGap,paint);

                //Draw body
                rotatedBitmap = bodyBitmap;
                for(int i = 1;i< snakeLength - 1; i++){
                    switch (snakeHead[i]){
                        case 0://Up
                            rotatedBitmap = Bitmap.createBitmap(bodyBitmap,0,0,rotatedBitmap.getWidth(),rotatedBitmap.getHeight(),matrix270,true);
                            break;
                        case 1://right
                            // no rotation
                            break;
                        case 2://down 90
                            rotatedBitmap = Bitmap.createBitmap(bodyBitmap,0,0,rotatedBitmap.getWidth(),rotatedBitmap.getHeight(),matrix90,true);
                            break;
                        case 3://left matrix 180
                            rotatedBitmap = Bitmap.createBitmap(bodyBitmap,0,0,rotatedBitmap.getWidth(),rotatedBitmap.getHeight(),matrix180,true);
                            break;

                    }

                    canvas.drawBitmap(rotatedBitmap,snakeX[i] * blockSize,(snakeY[i] * blockSize) + topGap,paint);
                }

                //Draw tail
                rotatedTailBitmap = tailBitmap;

                //rotatedTailBitmap = Bitmap.createBitmap(tailBitmap, flowerRectToBeDrawn.left,flowerRectToBeDrawn.top,flowerRectToBeDrawn.right - flowerRectToBeDrawn.left,flowerRectToBeDrawn.bottom);
                switch (snakeHead[snakeLength-1]){
                    case 0:
                        rotatedTailBitmap = Bitmap.createBitmap(tailBitmap,0,0,rotatedTailBitmap.getWidth(),rotatedTailBitmap.getHeight(),matrix270,true);
                        break;
                    case 1:
                        break;
                    case 2:
                        rotatedTailBitmap = Bitmap.createBitmap(tailBitmap,0,0,rotatedTailBitmap.getWidth(),rotatedTailBitmap.getHeight(),matrix90,true);
                        break;
                    case 3:
                        rotatedTailBitmap = Bitmap.createBitmap(tailBitmap,0,0,rotatedTailBitmap.getWidth(),rotatedTailBitmap.getHeight(),matrix180,true);
                        break;
                }
                canvas.drawBitmap(rotatedTailBitmap, snakeX[snakeLength -1] * blockSize,(snakeY[snakeLength-1]*blockSize) + topGap,paint);

                //Draw Apple
                canvas.drawBitmap(appleBitmap,appleX*blockSize,(appleY*blockSize) + topGap,paint);

                //Unlock canvas
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

        public void pause(){
            playingSnake = false;
            try{
                thread.join();
            }catch(InterruptedException e){

            }
        }
        public void resume(){
            playingSnake = true;
            thread = new Thread(this);
            thread.start();
        }

        @Override
        public boolean onTouchEvent(MotionEvent motionEvent){
            switch(motionEvent.getAction()&MotionEvent.ACTION_MASK){
                case MotionEvent.ACTION_UP:
                    if(motionEvent.getX() >= screenWidth/2){
                        //turn right
                        ++travelDirection;
                        if(travelDirection == 4) travelDirection = 0;
                    }else{
                        //turn left
                    }
            }
            return true;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            snakeView.pause();
            finish();
            return true;
        }
        return false;
    }

    public void loadSound(){

        if((android.os.Build.VERSION.SDK_INT) >= android.os.Build.VERSION_CODES.LOLLIPOP){

            createSoundPoolWithBuilder();
        }

        else  {
            createSoundPoolWithConstructor();
        }

        sound1 = soundPool.load(this, R.raw.sound1, 1);
        sound2 = soundPool.load(this, R.raw.sound2, 1);
        sound3 = soundPool.load(this, R.raw.sound3, 1);
        sound4 = soundPool.load(this, R.raw.sound4, 1);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void createSoundPoolWithBuilder() {
        AudioAttributes attributes = new AudioAttributes.Builder().
                setUsage(AudioAttributes.USAGE_GAME).
                setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build();

        soundPool = new SoundPool.Builder().setAudioAttributes(attributes).setMaxStreams(8).build();
    }

    @SuppressWarnings("deprecation")
    private void createSoundPoolWithConstructor() {
        soundPool = new SoundPool(8, AudioManager.STREAM_MUSIC, 0);
    }

    public void configureDisplay(){
        //find out the width and height of the screen
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        topGap = screenHeight/14;

        //Determine the size of each block/place on the game board
        blockSize = screenWidth/40;

        //Determine how many game blocks will fit into the height and width
        //Leave one block for the score at the top
        numBlocksWide = 40;
        numBlocksHigh = ((screenHeight - topGap ))/blockSize;

        //Load and scale bitmaps
        headBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.head);
        bodyBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.body);
        tailBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tail);
        appleBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.apple);

        //scale the bitmaps to match the block size
        headBitmap = Bitmap.createScaledBitmap(headBitmap, blockSize, blockSize, false);
        bodyBitmap = Bitmap.createScaledBitmap(bodyBitmap, blockSize, blockSize, false);
        tailBitmap = Bitmap.createScaledBitmap(tailBitmap, blockSize, blockSize, false);
        appleBitmap = Bitmap.createScaledBitmap(appleBitmap, blockSize, blockSize, false);

        //for the tail
        //tailBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tail_sprite_sheet);
        //tailBitmap = Bitmap.createScaledBitmap(tailBitmap, blockSize*flowerNumFrames, blockSize, false);

        //for the flower
        //flowerBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.flower_sprite_sheet);
        //flowerBitmap = Bitmap.createScaledBitmap(flowerBitmap, blockSize*flowerNumFrames, blockSize, false);

        //These two lines work for the flower and the tail
        //frameWidth=flowerBitmap.getWidth()/flowerNumFrames;
        //frameHeight=flowerBitmap.getHeight();

        //Initialize matrix objects ready for us in drawGame
        matrix90.postRotate(90);
        matrix180.postRotate(180);
        matrix270.postRotate(270);
        //And now the head flipper
        matrixHeadFlip.setScale(-1,1);
        matrixHeadFlip.postTranslate(headBitmap.getWidth(),0);

        //setup the first frame of the flower drawing
        //flowerRectToBeDrawn = new Rect((flowerFrameNumber * frameWidth), 0,
        //        (flowerFrameNumber * frameWidth +frameWidth)-1, frameHeight);

    }

    @Override
    protected void onResume() {
        super.onResume();
        snakeView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        snakeView.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        snakeView.pause();
        //finish();
    }
}


