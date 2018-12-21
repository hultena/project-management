package entities;

public class Risk {
    private String name;
    private float probability;
    private int severity;

    public Risk() {

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
    public float getImpact(){
        float impact = severity*probability;
        return impact;
    }
}