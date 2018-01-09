package com.cis2237.galczak_p3.rsi_balloon;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Chris on 9/30/2016.
 */


//Notes from
//https://developer.android.com/guide/topics/ui/layout/gridview.html


public class ImageAdapter extends BaseAdapter {
    //Variables
    public Context context;
    public static Integer[] photoId = {R.drawable.angry,R.drawable.beagle, R.drawable.cosmos1_ballloon, R.drawable.creamland,R.drawable.darth_vader, R.drawable.joelly_bee_ballon};// R.drawable.pirate_ship_ballon};//,R.drawable.mariachi_ballon,R.drawable.pirate_ship_ballon,R.drawable.suuny_boy_ballon,R.drawable.yoda};


    public ImageAdapter(Context c){
        context = c;
    }

    @Override
    public int getCount() {
        return photoId.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {


        ImageView imageView;

        if (view == null)
        {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(600,600));
            //imageView.getLayoutParams().height = 150;
            //imageView.getLayoutParams().width = 150;
            imageView.setMinimumHeight(400);
            imageView.setMinimumWidth(400);

            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(8,8,8,8);
        }
        else {
            imageView = (ImageView)view;
        }

        imageView.setImageResource(photoId[position]);
        return imageView;
    }

}

