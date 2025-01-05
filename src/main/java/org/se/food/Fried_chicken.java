package org.se.food;

import org.se.food.interpreter.Spiciness;

public class Fried_chicken extends Food {
    private Spiciness spiciness;

    public String getSpiciness() { return spiciness.getSpiciness(); }

    public void setSpiciness(String spiciness) { this.spiciness = new Spiciness(spiciness); }

    @Override
    public String toString() {
        return getName()+" ;Price: "+getPrice()+" ;Spiciness: "+spiciness.getSpiciness()+" ;Calories: "+getCalories()+" ;Weight: "+getWeight()+" ;Features"+getFeatures()+" ;";
    }
}
