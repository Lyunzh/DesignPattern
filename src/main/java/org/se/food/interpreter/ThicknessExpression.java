package org.se.food.interpreter;

public interface ThicknessExpression {
   void interpret(Thickness thickness);

   int greatThan(Thickness thickness1, Thickness thickness2);
}
