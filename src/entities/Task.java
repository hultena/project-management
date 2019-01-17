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

    public Task(String name, int budgetedHours, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.budgetedHours = budgetedHours;
        this.startDate = startDate;
        this.endDate = endDate;
        this.contributions = new ArrayList<Contribution>();
    }

    public Task() {
       this.contributions = new ArrayList<Contribution>();
    }

    public void addContribution(String id, int time, int percentageCompleted, LocalDate date){
        Contribution newContribution = new Contribution(id, time, percentageCompleted, date);
        contributions.add(newContribution);

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
    public double calculateDuration(){
        return ChronoUnit.DAYS.between(startDate,endDate);
    }
    public double calculateTaskBudget(int salary){
        return this.budgetedHours*salary;
    }
    public double actualPercentageCompleted(LocalDate date){
        double percentageCompleted = 0;
        for(Contribution contribution: contributions){
            if(contribution.getDate().isBefore(date)){
                percentageCompleted+=contribution.getPercentageCompleted();
            }
        }return percentageCompleted/100;
    }
    public double calculateSvTask(LocalDate date,int salary){
        return calculateEvTask(date,salary)-calculatePvTask(date,salary);
    }
    public double calculateEvTask(LocalDate date,int salary){
        return calculateTaskBudget(salary)*actualPercentageCompleted(date);
    }
    public double calculateAcTask(LocalDate date,int salary){
        return timeSpent(date)*salary;
    }
    public double calculateCvTask(LocalDate date,int salary){
        return calculateEvTask(date,salary)-calculateAcTask(date,salary);
    }
    public double calculatePvTask(LocalDate date,int salary){
        double duration = calculateDuration();
        double span = ChronoUnit.DAYS.between(startDate,date);
        if(span>duration){
            span=duration;
        }
        double ratio = span/duration;
        return calculateTaskBudget(salary)*ratio;
    }
    public String getName() {
        return this.name;
    }
    public int getBudgetedHours() {
        return this.budgetedHours;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public LocalDate getStartDate() {
        return startDate;
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

}
