package entities;

public class Risk {
    private String name;
    private int probability;
    private int severity;

    public Risk() {

    }

    public String toString() {
        return "| Name: "+this.name+" | Probability: "+this.probability+
                " | Severity :"+this.severity+" | Impact : "+this.probability*this.severity+" | "+System.lineSeparator();
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
    public int getImpact(){
        int impact = severity*probability;
        return impact;
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