package entities;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Task {
    private String name;
    private int budgetedHours;
    private LocalDate startDate;
    private LocalDate endDate;
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
    public void addContribution(String id, int time, int percentageCompleted, LocalDate date){
        Contribution newContribution = new Contribution();
        newContribution.setId(id);
        newContribution.setTimeSpent(time);
        newContribution.setPercentageCompleted(percentageCompleted);
        newContribution.setDate(date);
        contributions.add(newContribution);

    }
    public List<Contribution> getContributions(){
        return contributions;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setBudgetedHours(int budgetedHours) {
        this.budgetedHours = budgetedHours;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    public int completion(){
        int completion = 0;
        for(Contribution i : contributions){
            completion+=i.getPercentageCompleted();
        }
        return completion;
    }
    public int timeSpent(LocalDate date){
        int timeSpent = 0;
        for(Contribution i : contributions){
            if(date.isAfter(i.getDate()))
                timeSpent+=i.getTimeSpent();
        }
        return timeSpent;
    }
    public String toString(){
        return "| Name: "+name+" | Budgeted hours: "+budgetedHours+" | Start week: "+startDate
                +" | End week: "+endDate+" | Completion: " +completion()+"% | Time spent: "+timeSpent(LocalDate.now())+" hours. | "+System.lineSeparator();
    }
    public long calculateDuration(){
        return ChronoUnit.DAYS.between(startDate,endDate);
    }
    public double plannedPercentage(LocalDate date){
        long span = ChronoUnit.DAYS.between(startDate,date);
        return 100.0/span;
    }
    public double actualPercentageCompleted(LocalDate date){
        int percentageCompleted = 0;
        for(Contribution contribution: contributions){
            if(contribution.getDate().isBefore(date)){
                percentageCompleted+=contribution.getPercentageCompleted();
            }
        }return percentageCompleted;
    }
    public double calculateSvTask(LocalDate date){
        return actualPercentageCompleted(date)-plannedPercentage(date);
    }
    public double calculateEvTask(LocalDate date){
        return calculateDuration()*actualPercentageCompleted(date);
    }

}