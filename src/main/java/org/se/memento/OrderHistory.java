package org.se.memento;

import java.util.Stack;

public class OrderHistory {
    private Stack<OrderMemento> history = new Stack<>();
    
    public void push(OrderMemento state) {
        history.push(state);
    }
    
    public OrderMemento pop() {
        if(!history.isEmpty()) {
            return history.pop();
        }
        return null;
    }
} 