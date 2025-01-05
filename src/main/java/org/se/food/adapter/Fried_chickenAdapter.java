package org.se.food.adapter;

import org.jsoup.nodes.Element;
import org.se.food.Food;
import org.se.food.Fried_chicken;
//Refactored with Adapter pattern
public class Fried_chickenAdapter extends Food {


    public Fried_chicken fried_chicken;

    public Fried_chickenAdapter() {
        fried_chicken = new Fried_chicken();
    }

    @Override
    public void setAddition(Element e)
    {
        String originalAddition = e.getElementsContainingText("Spiciness: ").text();
        originalAddition = originalAddition.substring(11, originalAddition.length());
        fried_chicken.setSpiciness(originalAddition);
    }


    @Override
    public void TemplateMethod(Element e)
    {
        fried_chicken.TemplateMethod(e);
        setAddition(e);
    }
}
