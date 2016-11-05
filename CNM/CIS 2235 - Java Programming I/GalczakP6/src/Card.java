/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Anthony
 */
public class Card {
    private int rank;
    private String suit;
    private String imageName;
    
    public Card(){
        rank = 0;
        suit = "None";
        imageName = "WhatImage";
    }
    
    public Card(int r, String s, String i){
        rank = r;
        suit = s;
        imageName = i;
    }
    
    public int getRank(){
        return rank;
    }
    
    public String getSuit(){
        return suit;
    }
    
    public String getImageName(){
        return imageName;
    }
    
    public void setRank(int r){
        rank = r;
    }
    
    public void setSuit(String s){
        suit = s;
    }
    
    public void setImageName(String i){
        imageName = i;
    }
}
