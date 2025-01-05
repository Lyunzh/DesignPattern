package org.se.memento;

import org.se.food.Food;
import org.se.state.DiscountState;
import org.se.state.NoDiscountState;

import java.util.ArrayList;
import java.util.List;
//Refactored with momento\state pattern
public class Order {
    private List<Food> foods;
    private DiscountState discountState;
    
    public Order() {
        foods = new ArrayList<>();
        discountState = new NoDiscountState();
    }
    
    public void addFood(Food food) {
        foods.add(food);
    }
    
    public void setDiscountState(DiscountState state) {
        this.discountState = state;
    }

    public DiscountState getDiscountState() {
        return discountState;
    }
    
    public float getTotal() {
        return discountState.calculateTotal(foods);
    }
    
    public OrderMemento save() {
        return new OrderMemento(foods);
    }
    
    public void restore(OrderMemento memento) {
        foods = memento.getSavedFoods();
    }
    
    public List<Food> getFoods() {
        return new ArrayList<>(foods);
    }
} 