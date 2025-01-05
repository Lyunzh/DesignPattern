package org.se.food.adapter;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.se.food.Food;
import org.se.food.Pizza;
//Refactored with Adapter pattern
public class PizzaAdapter extends Food {


    public Pizza pizza;

    public PizzaAdapter() {
        pizza = new Pizza();
    }


    @Override
    public void setAddition(Element e)
    {
        String originalAddition = e.getElementsContainingText("Radius: ").text();
        originalAddition = StringUtils.substringBetween(originalAddition, "Radius: ", " inches");
        pizza.setRadius(Integer.parseInt(originalAddition));
    }


    @Override
    public void TemplateMethod(Element e)
    {
        pizza.TemplateMethod(e);
        setAddition(e);
    }





}
