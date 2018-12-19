package entities;

public class Risk {
    private String name;
    private float probability;
    private int severity;

    public Risk(String name, float probability, int severity) {
        this.name = name;
        this.probability = probability;
        this.severity = severity;
    }

    public String toString() {
        return this.name;
    }

    public String getName() {
        return name;
    }
    public float getProbability() {
        return probability;
    }
    public int getSeverity() {
        return severity;
    }
}