package com.cis2237.galczak_p3.starbuzz;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by anthony on 10/2/2016.
 */
public class ImageAdapter extends BaseAdapter {

    public Context context;
    // declare array of food images

    static int foodsLength = Food.foods.length;

    public static Integer[] imageIds = new Integer[foodsLength];


    public ImageAdapter(Context c){
        context = c;
    }

    @Override
    public int getCount(){
        return imageIds.length;
    }

    @Override
    public Object getItem(int i){
        return null;
    }

    @Override
    public long getItemId(int i ){
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup){
        ImageView imgView;

        // Initializing imageId array with foods array
        for (int i = 0; i < foodsLength; ++i){
            imageIds[i] = Food.foods[i].getImgResourceId();
        }

        // If the view is ready to be initialized and not populated then size it
        if (view == null){
            imgView = new ImageView(context);
            imgView.setLayoutParams(new GridView.LayoutParams(300,300));

            // Cropping the image for better look
            imgView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imgView.setPadding(12, 12, 12, 12);
        }
        else{
            imgView = (ImageView)view;
        }

        imgView.setImageResource(imageIds[position]);
        return imgView;
    }
}
