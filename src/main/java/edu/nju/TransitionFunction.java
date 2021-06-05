package edu.nju;

/**
 * @Author: pkun
 * @CreateTime: 2021-05-23 16:19
 */
public class TransitionFunction {

    String fromState;
    String toState;
    String input;
    String output;
    String direction;

    public TransitionFunction(String fromState, String toState, String input, String output, String direction) {
        this.fromState = fromState;
        this.toState = toState;
        this.input = input;
        this.output = output;
        this.direction = direction;
    }

    public TransitionFunction(String s) {
        String[] var = s.split(" ");
        this.fromState = var[0];
        this.input = var[1];
        this.output = var[2];
        this.direction = var[3];
        this.toState = var[4];
    }

    public String getFromState() {
        return fromState;
    }

    public void setFromState(String fromState) {
        this.fromState = fromState;
    }

    public String getToState() {
        return toState;
    }

    public void setToState(String toState) {
        this.toState = toState;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "#D " + fromState + " " + input + " " + output + " " + direction + " " + toState + System.lineSeparator();
    }
}
