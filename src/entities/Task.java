package entities;
import java.util.*;

public class Task {
    private String name;
    private int budgetedHours;

    private int startWeek;
    private int endWeek;
    private List<Contribution> contributions;

    public Task() {
        contributions = new ArrayList<Contribution>();
    }

    public String getName() {
        return this.name;
    }
    public int getBudgetedHours() {
        return this.budgetedHours;
    }
    public void addContribution(String id, int time, int percentageCompleted){
        Contribution i = findContribution(id);
        if(i!=null&&i.getId().equalsIgnoreCase(id)){
            i.addTime(time);
            i.addPercentage(percentageCompleted);
        }
        else{

            Contribution newContribution = new Contribution();

            contributions.add(newContribution);
        }
    }
    public List<Contribution> getContributions(){
        return contributions;
    }
    public Contribution findContribution(String id) {

        for (Contribution i : contributions) {
            if (i.getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return null;
    }
    public int printTime(String id){
        int time = 0;
        if(findContribution(id)!=null) {
            time = findContribution(id).getTime();
        }
        return time;
    }
    public int printPercentage(String id){
        int percentage = 0;
        if(findContribution(id)!=null) {
            percentage = findContribution(id).getTime();
        }
        return percentage;
    }

    public void setEndWeek(int endWeek) {
        this.endWeek = endWeek;
    }

    public void setBudgetedHours(int budgetedHours) {
        this.budgetedHours = budgetedHours;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartWeek(int startWeek) {
        this.startWeek = startWeek;
    }

    public int getEndWeek() {
        return endWeek;
    }

    public int getStartWeek() {
        return startWeek;
    }
    public int getCompletion(){
        int completion = 0;
        for(Contribution i : contributions){
            completion+=i.getPercentageCompleted();
        }
        return completion;
    }
    public int getTimeSpent(){
        int timeSpent = 0;
        for(Contribution i : contributions){
            timeSpent+=i.getTimeSpent();
        }
        return timeSpent;
    }
    public String toString(){
        return System.lineSeparator()+" | Name: "+name+" | Budgeted hours: "+budgetedHours+" | Completion: "
                +getCompletion()+"% | Time spent: "+getTimeSpent()+" hours. | ";
    }

}