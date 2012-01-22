package net.devonlinux.datamunging.core;

public class SpreadInformation {

    private SpreadCalculator spreadCalculator;
    private String name;
    private int maxValue;
    private int minValue;

    public SpreadInformation(String name, int maxValue, int minValue) {
        this(new SpreadCalculator(), name, maxValue, minValue);
    }

    public SpreadInformation(SpreadCalculator spreadCalculator, String name, int maxValue, int minValue) {
        this.spreadCalculator = spreadCalculator;
        this.name = name;
        this.maxValue = maxValue;
        this.minValue = minValue;
    }

    public String name(){
        return  name;
                
    }
    
    public int spread(){
        return spreadCalculator.calculate(maxValue, minValue);
    }

}
