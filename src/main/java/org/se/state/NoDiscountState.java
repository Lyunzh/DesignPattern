package org.se.state;

import org.se.food.Food;
import java.util.List;

public class NoDiscountState implements DiscountState {
    @Override
    public float calculateTotal(List<Food> foods) {
        float total = 0;
        for(Food food : foods) {
            total += food.getPrice();
        }
        return total;
    }

    @Override
    public String getDiscountName() {
        return "No Discount";
    }
} 