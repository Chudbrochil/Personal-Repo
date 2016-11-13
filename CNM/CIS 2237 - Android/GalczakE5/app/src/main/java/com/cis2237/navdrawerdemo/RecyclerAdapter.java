//package com.cis2237.navdrawerdemo;
//
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//
///**
// * Created by anthony on 11/10/2016.
// */
//public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
//
//    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.activity_card, parent, false);
//        RecyclerView.ViewHolder viewHolder = new RecyclerView.ViewHolder(v);
//        return viewHolder;
//    }
//
//    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
//        holder.itemTitle.setText(titles[position]);
//        holder.itemDetail.setText(details[position]);
//        holder.itemImage.setImageResource(images[position]);
//    }
//
//    public getItemCount() {
//        return titles.length;
//    }
//
//
//
//    private String[] titles = {"Chapter One",
//            "Chapter Two",
//            "Chapter Three",
//            "Chapter Four",
//            "Chapter Five",
//            "Chapter Six",
//            "Chapter Seven",
//            "Chapter Eight"};
//
//    private String[] details = {"Item one details",
//            "Item two details", "Item three details",
//            "Item four details", "Item file details",
//            "Item six details", "Item seven details",
//            "Item eight details"};
//
//    private int[] images = {R.drawable.android_image_1,
//            R.drawable.android_image_2,
//            R.drawable.android_image_3,
//            R.drawable.android_image_4,
//            R.drawable.android_image_5,
//            R.drawable.android_image_6,
//            R.drawable.android_image_7,
//            R.drawable.android_image_8};
//
//
//    class ViewHolder extends RecyclerView.ViewHolder(View itemView) {
//        super(itemView);
//        itemImage =
//                (ImageView)itemView.findViewById(R.id.item_image);
//        itemTitle =
//                (TextView)itemView.findViewById(R.id.item_title);
//        itemDetail =
//                (TextView)itemView.findViewById(R.id.item_detail);
//
//    }
//}
//
//}
