package org.se.state;

import org.se.food.Food;
import java.util.List;

public class Over100DiscountState implements DiscountState {
    @Override
    public float calculateTotal(List<Food> foods) {
        float total = 0;
        for(Food food : foods) {
            total += food.getPrice();
        }
        return total > 100 ? total - 50 : total;
    }

    @Override
    public String getDiscountName() {
        return "$50 off when over $100";
    }
} 