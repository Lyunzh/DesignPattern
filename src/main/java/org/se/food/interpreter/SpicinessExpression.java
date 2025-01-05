package org.se.food.interpreter;
//Refactored with interpreter pattern
public interface SpicinessExpression
{
    void interpret(Spiciness spiciness);

    int greatThan(Spiciness spiciness1, Spiciness spiciness2);
}
