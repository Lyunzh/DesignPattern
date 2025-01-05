package org.se.food;

import org.se.food.interpreter.Thickness;

public class French_fries extends Food {
    private Thickness thickness;

    public String getThickness() { return "thickness: "+thickness.getThickness(); }

    public void setThickness(String thickness) { this.thickness = new Thickness(thickness);}


    @Override
    public String toString()
    {
        return getName()+" ;Price: "+getPrice()+" ;Thickness: "+thickness.getThickness()+" ;Calories: "+getCalories()+" ;Weight: "+getWeight()+" ;Features"+getFeatures()+" ;";
    }

}
