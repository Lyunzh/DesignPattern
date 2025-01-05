package org.se.state;

import org.se.food.Food;
import org.se.food.Pizza;
import java.util.List;

public class PizzaHalfPriceState implements DiscountState {
    @Override
    public float calculateTotal(List<Food> foods) {
        float total = 0;
        for(Food food : foods) {
            if(food instanceof Pizza && food.getName().contains("Margherita")) {
                total += food.getPrice() * 0.5;
            } else {
                total += food.getPrice();
            }
        }
        return total;
    }

    @Override
    public String getDiscountName() {
        return "50% off Margherita Pizza";
    }
} 