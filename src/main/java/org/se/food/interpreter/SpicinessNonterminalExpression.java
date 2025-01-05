package org.se.food.interpreter;

import java.beans.Expression;
//Refactored with interpreter pattern
public class SpicinessNonterminalExpression implements SpicinessExpression {
    private SpicinessExpression[] expressions;

    public SpicinessNonterminalExpression(SpicinessExpression...expressions) {
        this.expressions = expressions;
    }

    @Override
    public void interpret(Spiciness context) {
        for (SpicinessExpression expression : expressions) {
            expression.interpret(context);
        }
    }

    @Override
    public int greatThan(Spiciness s1, Spiciness s2) {
        for (SpicinessExpression expression : expressions) {
            expression.interpret(s1);
            expression.interpret(s2);
        }
        return s1.getOutput()>s2.getOutput()?1:0;
    }

}
