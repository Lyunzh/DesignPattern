package org.se.food.interpreter;

public class ThicknessTerminalExpression implements ThicknessExpression{
    private String key;
    private int value;

    public ThicknessTerminalExpression(String key, int value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public void interpret(Thickness context) {
        if (context.getThickness().equals(key)) {
            context.setOutput(value);
        }
    }

    @Override
    public int greatThan(Thickness context1,Thickness context2)
    {
        return 0;
    }

}
