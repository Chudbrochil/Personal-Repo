package com.cis2237.galczak_p3.starbuzz;

/**
 * Created by anthony on 9/29/2016.
 */
public class Food {

    private String name;
    private String description;
    private int imgResourceId;
    private double cost;

    // Declaring static array of foods for use in grid
    public static Food[] foods = {
        new Food("Cheeseburger", "A delicious cheeseburger", R.drawable.cheeseburger, 4.99),
        new Food("Corn Dog", "Tasty, greasy corn dog", R.drawable.corndog, 2.99),
        new Food("Hot Dog", "Baseball style all-beef hot dog", R.drawable.hotdog, 3.99),
        new Food("Apple Pie", "Grandma's homemade Applie pie", R.drawable.apple_pie, 2.99),
        new Food("Baked Beans", "Beans with bits of pork", R.drawable.baked_beans, 1.99),
        new Food("Cole Slaw", "Side of southern-style Cole Slaw", R.drawable.cole_slaw, 1.99),
        new Food("Fried Pickles", "Side of fried pickles", R.drawable.fried_pickles, 2.99),
        new Food("Fries", "Wedge-cut french fries", R.drawable.fries, 2.99),
        new Food("Grilled Cheese", "Grilled cheese with 3 different cheeses", R.drawable.grilled_cheese, 4.99),
        new Food("Mac and Cheese", "Baked mac and cheese", R.drawable.mac_n_cheese, 3.99),
        new Food("Pork Ribs", "Half rack of Ribs", R.drawable.pork_ribs, 6.99),
        new Food("Potato Salad", "Side of potato salad", R.drawable.potato_salad, 2.99)};

    public Food(String nm, String desc, int imgId, double cst){
        this.name = nm;
        this.description = desc;
        this.imgResourceId = imgId;
        this.cost = cst;

    }

    public String getName() {
        return name;
    }

    // Adding toString method per requirements
    public String toString(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImgResourceId() {
        return imgResourceId;
    }

    public void setImgResourceId(int imgResourceId) {
        this.imgResourceId = imgResourceId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
