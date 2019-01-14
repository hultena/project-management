package entities;

public class Contribution {
    private String id;

    private int timeSpent;
    private int percentageCompleted;
    private int week;

    public Contribution(){

    }
    public String getId(){
        return id;
    }

    public int getPercentageCompleted(){
        return percentageCompleted;
    }

    public String toString() {
        return "ID: "+id+" | Time: "+ timeSpent+" | Percent completed: "+percentageCompleted+"% | Week added: "+week;
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

    public void setWeek(int week){
        this.week = week;
    }
    public int getWeek(){
        return week;
    }

}
