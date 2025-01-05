package org.se.food;


import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;

public class Food {

    private String name;

    private int weight;

    private int calories;

    private float price;

    private String features;

    public void setName(Element e) {
        this.name = e.select("h2").text();
    }

    public String getName() {return "name: "+name;}

    public void setWeight(Element e){
        String originalWeight = e.getElementsContainingText("Weight").text();
        originalWeight = StringUtils.substringBetween(originalWeight, "Weight: ", "g");
        this.weight = Integer.parseInt(originalWeight);
    }

    public int getWeight() {return weight;}

    public void setCalories(Element e){
        String orginalCal = e.getElementsContainingText("Calories").text();
        orginalCal = StringUtils.substringBetween(orginalCal, "Calories: ", " kcal");
        this.calories = Integer.parseInt(orginalCal);
    }

    public int getCalories() {return calories;}

    public void setPrice(Element e){
        String originalPrice = e.getElementsByClass("price").text().substring(1);
        this.price = Float.parseFloat(originalPrice);
    }

    public float getPrice() {return price;}

    public void setFeatures(Element e){
        this.features = e.getElementsByClass("features").text().substring(10);
    }

    public String getFeatures() {return  "Features: "+features;}


    public void setAddition(Element e){}

    public String getAddtion(){
        return null;
    }

    public void TemplateMethod(Element e){
        setAddition(e);
        setWeight(e);
        setCalories(e);
        setPrice(e);
        setFeatures(e);
        setName(e);
    }

    @Override
    public String toString() {
        return name+weight+"g"+calories+features+price;
    }

}
