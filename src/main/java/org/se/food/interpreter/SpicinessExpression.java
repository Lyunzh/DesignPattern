package org.se.food.interpreter;

public interface SpicinessExpression
{
    void interpret(Spiciness spiciness);

    int greatThan(Spiciness spiciness1, Spiciness spiciness2);
}
