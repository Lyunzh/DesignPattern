package org.se.state;

import org.se.food.Food;
import java.util.List;

public interface DiscountState {
    float calculateTotal(List<Food> foods);
    String getDiscountName();
} 