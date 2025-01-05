package org.se.food.interpreter;

public class SpicinessTerminalExpression implements SpicinessExpression {

    private String key;
    private int value;

    public SpicinessTerminalExpression(String key, int value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public void interpret(Spiciness context) {
        if (context.getSpiciness().equals(key)) {
            context.setOutput(value);
        }
    }

    @Override
    public int greatThan(Spiciness context1,Spiciness context2)
    {
        return 0;
    }





}
