package entities;

public class Contribution {
    private String id;
    private int timeSpent;
    private int percentageCompleted;

    public Contribution(){

    }
    public String getId(){
        return id;
    }

    public int getPercentageCompleted(){
        return percentageCompleted;
    }
    public void addTime(int time){
        this.timeSpent+=time;
    }
    public void addPercentage(int percentage){
        this.percentageCompleted+=percentage;
    }
    public String toString() {
        return "ID: "+id+" | Time: "+ timeSpent+" | Percent completed: "+percentageCompleted+"%";
    }

    public int getTimeSpent() {
        return timeSpent;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPercentageCompleted(int percentageCompleted) {
        this.percentageCompleted = percentageCompleted;
    }

    public void setTimeSpent(int timeSpent) {
        this.timeSpent = timeSpent;
    }

}
