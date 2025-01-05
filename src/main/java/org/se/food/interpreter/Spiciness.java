package org.se.food.interpreter;

public class Spiciness {
    private String spiciness;

    private int output;

    public Spiciness(String spiciness) {
        this.spiciness = spiciness;
        output = 0;
    }

    public String getSpiciness() {
        return spiciness;
    }
    public void setSpiciness(String Spiciness) {
        this.spiciness = Spiciness;
    }
    public int getOutput() {
        return output;
    }
    public void setOutput(int output) {
        this.output = output;
    }
}
