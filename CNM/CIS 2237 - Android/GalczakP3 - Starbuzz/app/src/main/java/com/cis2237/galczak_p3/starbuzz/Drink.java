package com.cis2237.galczak_p3.starbuzz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Drink{
    private String name;
    private double price;
    private String description;
    private int imgResource;

    // Initializing static array of Drink objects
    public static final Drink[] drinks = {
            new Drink("Coca Cola", 1.99, "Cool, refreshing Coca-Cola in a glass bottle", R.drawable.coca_cola),
            new Drink("Sweet Tea", 1.99, "Alabama sweet tea served in a mason jar", R.drawable.sweet_tea),
            new Drink("Beer", 4.99, "Domestic draft beer in a mug", R.drawable.beer)
    };

    public Drink(){
        this.name = "Beer";
        this.price = 4.99;
        this.description = "It's a Beer!";
        this.imgResource = R.drawable.beer;
    }

    public Drink(String name, double price, String desc, int imgRes){
        this.name = name;
        this.price = price;
        this.description = desc;
        this.imgResource = imgRes;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }
}