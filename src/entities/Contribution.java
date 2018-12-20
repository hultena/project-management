package entities;

public class Contribution {
    private String id;
    private int time;
    private int percentageCompleted;

    public Contribution(String id, int time, int percentageCompleted){
        this.id = id;
        this.time = time;
    }
    public String getId(){
        return id;
    }
    public int getTime(){
        return time;
    }
    public void addTime(int time){
        this.time+=time;
    }
    public void addPercentage(int percentage){
        this.percentageCompleted+=percentage;
    }
    public String toString() {

        String contributions = "id: "+id+" time: "+ time+System.lineSeparator();

        return contributions;
    }
}
