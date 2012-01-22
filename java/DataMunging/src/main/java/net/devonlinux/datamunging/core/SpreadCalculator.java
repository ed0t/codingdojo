package net.devonlinux.datamunging.core;

public class SpreadCalculator {
    public int calculate(int maxValue, int minValue) {
        return  Math.abs(maxValue - minValue);
    }
}
