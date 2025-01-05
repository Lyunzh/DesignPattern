package org.se.food.adapter;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.se.food.Food;
import org.se.food.French_fries;

public class French_friesAdapter extends Food {

    public French_fries fries;


    public French_friesAdapter() {
        fries = new French_fries();
    }

    @Override
    public void setAddition(Element e)
    {
        String originalAddition = e.getElementsContainingText("Thickness: ").text();
        originalAddition = originalAddition.substring(11, originalAddition.length());
        fries.setThickness(originalAddition);
    }


    @Override
    public void TemplateMethod(Element e)
    {
        fries.TemplateMethod(e);
        setAddition(e);
    }



}
