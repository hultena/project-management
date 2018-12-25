package entities;

public class Contribution {
    private String id;
<<<<<<< HEAD
    private int time;
    private int percentageCompleted;

    public Contribution(String id, int time, int percentageCompleted){
        this.id = id;
        this.time = time;
        this.percentageCompleted = percentageCompleted;
=======
    private int timeSpent;
    private int percentageCompleted;

    public Contribution(){

>>>>>>> develop
    }
    public String getId(){
        return id;
    }
    public int getTime(){
<<<<<<< HEAD
        return time;
=======
        return timeSpent;
>>>>>>> develop
    }
    public int getPercentageCompleted(){
        return percentageCompleted;
    }
    public void addTime(int time){
<<<<<<< HEAD
        this.time+=time;
=======
        this.timeSpent+=time;
>>>>>>> develop
    }
    public void addPercentage(int percentage){
        this.percentageCompleted+=percentage;
    }
    public String toString() {

<<<<<<< HEAD
        String contributions = "id: "+id+" time: "+ time+" percentage completed: "+System.lineSeparator();

        return contributions;
    }
=======
        String contributions = "Id: "+id+" | Time: "+ timeSpent+" | Percent completed: "+percentageCompleted+"%";

        return contributions;
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

>>>>>>> develop
}
