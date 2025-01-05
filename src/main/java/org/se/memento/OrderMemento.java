package org.se.memento;

import org.se.food.Food;
import java.util.ArrayList;
import java.util.List;
//Refactored with momento pattern
public class OrderMemento {
    private List<Food> foods;
    
    public OrderMemento(List<Food> foods) {
        this.foods = new ArrayList<>(foods);
    }
    
    public List<Food> getSavedFoods() {
        return new ArrayList<>(foods);
    }
} 