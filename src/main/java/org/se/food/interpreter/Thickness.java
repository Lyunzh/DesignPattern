package org.se.food.interpreter;

public class Thickness {
    private String thickness;




    private int output;

    public Thickness(String thickness) {
        this.thickness = thickness;
        output=0;
    }

    public void setThickness(String thickness) {
        this.thickness = thickness;
    }
    public String getThickness() {
        return thickness;
    }
    public void setOutput(int output) {
        this.output = output;
    }
    public int getOutput() {
        return output;
    }

}
