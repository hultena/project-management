package entities;
import utils.Output;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Project {
    public List<Risk> riskMatrix;
    public List<Task> tasks;
    public List<Member> members;
    private String projectName;
    private LocalDate startDate;
    private LocalDate endDate;
    private int engineerSalary;
    private int budget;

    public Project() {
        this.riskMatrix = new ArrayList<Risk>();
        this.tasks = new ArrayList<Task>();
        this.members = new ArrayList<Member>();
    }

    public void addTask(String name, int budgetedHours, LocalDate startDate, LocalDate endDate){
        Task newTask = new Task();
        newTask.setName(name);
        newTask.setBudgetedHours(budgetedHours);
        newTask.setStartDate(startDate);
        newTask.setEndDate(endDate);
        tasks.add(newTask);
    }

    public void addRisk(String name,int severity,int probability){
        Risk newRisk = new Risk();
        newRisk.setName(name);
        newRisk.setSeverity(severity);
        newRisk.setProbability(probability);
        riskMatrix.add(newRisk);
    }

    public List<Member> getMembers() {
        return this.members;
    }

    public void addMember(String name, String id) {
        if (this.findMember(id) == null) {
            Member newMember = new Member();
            newMember.setId(id);
            newMember.setName(name);
            this.members.add(newMember);
            System.out.println("Member successfully added.");
        } else {
            System.out.println("A member already exists with the ID: " + id);
        }
    }

    public void removeMember(String id) {
        Member memberToRemove = this.findMember(id);
        if (memberToRemove != null) {
            this.members.remove(memberToRemove);
            System.out.println("Member successfully removed.");
        } else {
            System.out.println("No member exists with the ID: " + id);
        }
    }

    public Member findMember(String id) {
        Member foundMember = null;
        for (Member i : members) {
            if (id.equalsIgnoreCase(i.getId())) {
                foundMember = i;
            }
        }
        return foundMember;
    }

    public double calculateEV(LocalDate date){
        double totalEV=0;
        for (Task task : tasks){
            totalEV+=task.calculateEvTask(date,engineerSalary);
        }
        return totalEV;
    }
    public double costOfPerformed(LocalDate date){
        int actualCost = 0;
        for(Task task : this.tasks) {
            actualCost+=task.calculateAcTask(date,engineerSalary);
        }
        return actualCost;
    }
    public double calculateSV(LocalDate date){
        return calculateEV(date)-calculatePV(date);
    }
    public double calculateCV(LocalDate date){
        return calculateEV(date)-costOfPerformed(date);
    }
    public double calculatePV(LocalDate date){
        double totalPV = 0;
        for(Task task : tasks){
            totalPV+=task.calculatePvTask(date,engineerSalary);
        }
        return totalPV;
    }
    public double calculateDuration(){
        return ChronoUnit.DAYS.between(startDate,endDate);
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public void setStartDate(LocalDate startDate){
        this.startDate = startDate;
    }
    public void setProjectName(String projectName){
        this.projectName = projectName;
    }

    public LocalDate getStartDate(){
        return startDate;
    }
    public LocalDate getEndDate(){
        return endDate;
    }
    public String getProjectName(){
        return projectName;
    }
    public int getEngineerSalary(){
        return engineerSalary;
    }
    public void setEngineerSalary(int engineerSalary) {
        this.engineerSalary = engineerSalary;
    }
    public int totalTimeWorked(String id){
        int time = 0;
        for (Task i : tasks){
            List<Contribution> contributions = i.getContributions();
            for(Contribution j : contributions){
                if(j.getId().equalsIgnoreCase(id)){
                    time+=j.getTimeSpent();
                }
            }
        }return time;
    }
    public void printAllContributionsByMember(String id){
        List<Contribution> memberContributions = new ArrayList<Contribution>();
        List<Task> memberTasks = new ArrayList<Task>();
        for(Task task : tasks){
            List<Contribution> contributions = task.getContributions();
            for(Contribution cont : contributions){
                if(cont.getId().equalsIgnoreCase(id)){
                    memberContributions.add(cont);
                    memberTasks.add(task);
                }
            }
        }
        Output.printAllContributions(memberContributions, memberTasks, this.findMember(id).getName());
    }

    public int getBudget(){
        return budget;
    }
    public void setBudget(int budget){
        this.budget=budget;
    }
    public int moneySpent(LocalDate date){
        int time = 0;
        for(Task task : tasks){
            List<Contribution> contributions = task.getContributions();
            for(Contribution contribution : contributions){
                if(contribution.getDate().isBefore(date)) {
                    time += contribution.getTimeSpent();
                }
            }
        }
        return time*engineerSalary;
    }
    public static LocalDate createDate(String input){
        int year = Integer.parseInt(input.substring(0,4));
        int month = Integer.parseInt(input.substring(4,6));
        int day = Integer.parseInt(input.substring(6,8));
        return LocalDate.of(year,month,day);
    }
}