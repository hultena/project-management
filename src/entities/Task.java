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
    public void addContribution(String id, int time, int percentageCompleted, int week){
        Contribution newContribution = new Contribution();
        newContribution.setId(id);
        newContribution.setTimeSpent(time);
        newContribution.setPercentageCompleted(percentageCompleted);
        newContribution.setWeek(week);
        contributions.add(newContribution);

    }
    public List<Contribution> getContributions(){
        return contributions;
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
    public int completion(){
        int completion = 0;
        for(Contribution i : contributions){
            completion+=i.getPercentageCompleted();
        }
        return completion;
    }
    public int timeSpent(){
        int timeSpent = 0;
        for(Contribution i : contributions){
            timeSpent+=i.getTimeSpent();
        }
        return timeSpent;
    }
    public String toString(){
        return "| Name: "+name+" | Budgeted hours: "+budgetedHours+" | Start week: "+startWeek
                +" | End week: "+endWeek+" | Completion: " +completion()+"% | Time spent: "+timeSpent()+" hours. | "+System.lineSeparator();
    }
    public int calculateTimeSpan(){
        if(startWeek<endWeek){
            return endWeek-startWeek;
        }else{
            return endWeek-startWeek+52;
        }
    }

}