package com.cis2237.galczak_p3.rsi_balloon;

import java.util.ArrayList;

/**
 * Created by huynh on 9/30/2016.
 */
public class Balloon
{
    private String OwnerName;
    private String balloonName;
    private String locationName;
    private int imgResource;

    public Balloon(){

    }

    public Balloon(String OwnerName,String balloonName,String locationName, int imgResource)
    {
        this.OwnerName = OwnerName;
        this.balloonName = balloonName;
        this.locationName = locationName;
        this.imgResource = imgResource;
    }
    public String getOwnerName(){
        return OwnerName;
    }
    public void setOwnerName(String Ownername){
        this.OwnerName = Ownername;
    }
    public String getBalloonName(){
        return balloonName;
    }
    public void setballoonName(String balloonName){
        this.balloonName = balloonName;
    }
    public String getLocationName(){
        return locationName;
    }
    public void LocationName(String locationName){
        this.locationName = locationName;
    }
    public int getImgResource(){
        return imgResource;
    }
    public void setImgResource(int img){
        this.imgResource = img;
    }
    public ArrayList<Balloon> getBalloonList()
    {
        ArrayList<Balloon> balloons = new ArrayList<Balloon>();
        balloons.add(new Balloon("Dale Ritchie","Elsie Creamland Cow","Golden British Columbia-Canada", R.drawable.creamland));
        balloons.add(new Balloon("Benoit Lambert","Darth","Court St. Etinne - Belgium", R.drawable.darth_vader));
        balloons.add(new Balloon("Andrew Holly","Pirate Ship","Benedict Savio", R.drawable.pirate_ship_ballon));
        balloons.add(new Balloon("Dale Ritchie","Angry Bird","Golden British Columbia-Canada", R.drawable.angry));
        balloons.add(new Balloon("Bob Romaneschi","Joelly (Bee)","Chandler, AZ - United States", R.drawable.joelly_bee_ballon));
        balloons.add(new Balloon("Jerry Holmes","Cosmos1","Fort Worth, TX - United States", R.drawable.cosmos1_ballloon));
        balloons.add(new Balloon("Master Yoda","Benoit Lambert","Court St. Etinne - Belgium", R.drawable.yoda));
        balloons.add(new Balloon("Mr. Mariachi","Mariel Merino Escamilla","Kingsport, TN - United States", R.drawable.mariachi_ballon));
        balloons.add(new Balloon("Sunny Boy","Wayne Fortney","Kingsport, TN - United States", R.drawable.suuny_boy_ballon));
        balloons.add(new Balloon("Beagle Maximus","James Cassell","Albuquerque, NM - United States", R.drawable.beagle));

        return balloons;
    }


}
