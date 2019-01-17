package entities;

public class Risk {
    private String name;
    private int probability;
    private int severity;

    public Risk(String name, int probability, int severity) {
        this.name = name;
        this.probability = probability;
        this.severity = severity;
    }

    public Risk() {} // empty constructor for jackson

    public int impact(){
        int impact = severity*probability;
        return impact;
    }
    public String getName() {
        return name;
    }
    public int getProbability() {
        return probability;
    }
    public int getSeverity() {
        return severity;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setProbability(int probability){
        this.probability=probability;
    }
    public void setSeverity(int severity){
        this.severity=severity;
    }
}
