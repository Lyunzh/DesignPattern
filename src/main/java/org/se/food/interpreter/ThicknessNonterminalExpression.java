package org.se.food.interpreter;

import java.beans.Expression;

public class ThicknessNonterminalExpression implements ThicknessExpression {
    private ThicknessExpression[] expressions;

    public ThicknessNonterminalExpression(ThicknessExpression... expressions) {
        this.expressions = expressions;
    }

    @Override
    public void interpret(Thickness context) {
        for (ThicknessExpression expression : expressions) {
            expression.interpret(context);
        }
    }

    @Override
    public int greatThan(Thickness t1, Thickness t2)
    {
        for (ThicknessExpression expression : expressions) {
            expression.interpret(t1);
            expression.interpret(t2);
        }
        return t1.getOutput()>t2.getOutput() ? 1 : 0;
    }

}
