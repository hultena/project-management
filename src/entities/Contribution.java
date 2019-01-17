package entities;

import java.time.LocalDate;

public class Contribution {
    private String id;
    private int timeSpent;
    private int percentageCompleted;
    private LocalDate date;

    public Contribution(){

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
    public void setDate(LocalDate date){
        this.date = date;
    }
    public LocalDate getDate(){
        return date;
    }
    public String getId(){
        return id;
    }
    public int getTimeSpent() {
        return timeSpent;
    }
    public int getPercentageCompleted(){
        return percentageCompleted;
    }

}
