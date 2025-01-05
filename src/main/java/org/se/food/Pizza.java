package org.se.food;

public class Pizza extends Food {
    private int radius;

    public int getRadius() { return radius; }

    public void setRadius(int r) {  radius = r; }

    @Override
    public String toString() {
        return getName()+" ;Price: "+getPrice()+" ;Radius: "+getRadius()+" ;Calories: "+getCalories()+" ;Weight: "+getWeight()+" ;Features"+getFeatures()+" ;";
    }

}
