package edu.nju;

import java.util.Map;
import java.util.Objects;

/**
 * @Author: pkun
 * @CreateTime: 2021-05-23 16:19
 */
public class TransitionFunction {

    private State sourceState;
    private State destinationState;
    private String input;
    private String output;
    private String direction;

    public TransitionFunction() {}

    public TransitionFunction(State sourceState, State destinationState, String input, String output, String direction) {
        this.sourceState = sourceState;
        this.destinationState = destinationState;
        this.input = input;
        this.output = output;
        this.direction = direction;
    }

    public TransitionFunction(String s, Map<String, State> Q) {
        String[] var = s.split(" ");
        this.sourceState = Q.get(var[0]);
        this.input = var[1];
        this.output = var[2];
        this.direction = var[3];
        this.destinationState = Q.get(var[4]);
    }

    public State getSourceState() {
        return sourceState;
    }

    public String getInput() {
        return input;
    }

    public State getDestinationState() {
        return destinationState;
    }

    public String getOutput() {
        return output;
    }

    public String getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "#D " + sourceState + " " + input + " " + output + " " + direction + " " + destinationState + System.lineSeparator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransitionFunction that = (TransitionFunction) o;
        return getSourceState().equals(that.getSourceState()) && getInput().equals(that.getInput());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSourceState(), getInput());
    }

}
