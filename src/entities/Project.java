package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import utils.Parser;
import java.util.*;

public class Project {
    public List<Risk> riskMatrix;
    public List<Task> tasks;
    public List<Member> members;
    public int projectDuration;
    public String projectName;
    public int startWeek;
    public int endWeek;
    public int engineerSalary;

    public Project() {
        this.riskMatrix = new ArrayList<Risk>();
        this.tasks = new ArrayList<Task>();
        this.members = new ArrayList<Member>();

    }

    public void addTask(String name, int budgetedHours, int startWeek, int endWeek){
        Task newTask = new Task();
        newTask.setName(name);
        newTask.setBudgetedHours(budgetedHours);
        newTask.setStartWeek(startWeek);
        newTask.setEndWeek(endWeek);
        tasks.add(newTask);
    }
    public void addRisk(String name,int severity,int probability){
        Risk newRisk = new Risk();
        newRisk.setName(name);
        newRisk.setSeverity(severity);
        newRisk.setProbability(probability);
        riskMatrix.add(newRisk);
    }

    public void setProjectDuration(int projectDurationWeeks) {
        this.projectDuration = projectDurationWeeks;
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

    public double meanPercentage(){
        double totalPercent = 0;
        int numberOfTasks = 0;
        double meanPercentage = 0;

        for (Task task : tasks) {
            totalPercent = totalPercent + task.completion();
            numberOfTasks += 1;
        }

        if (numberOfTasks != 0) {
            meanPercentage = totalPercent/numberOfTasks;
        }

        return meanPercentage;
    }

    public double calculateEV(){
        double EV = 0;
        double completedPercent = meanPercentage();
        double BAC = costOfPerformed();

        if(BAC != 0){
            EV = completedPercent/BAC;
        }
        return EV;
    }

    public double costOfPerformed(){
        int hoursSpent = 0;

        for(Task task : this.tasks) {
            hoursSpent += task.timeSpent();
        }
        return hoursSpent * engineerSalary;
    }

    public double costOfScheduled(){
        int totalHoursPlanned = 0;

        for(Task task: this.tasks) {
            totalHoursPlanned += task.getBudgetedHours();
        }
        return totalHoursPlanned * engineerSalary;
    }

    public double calculateSV(){
        return this.costOfPerformed() - this.costOfScheduled();
    }

    public double calculateCV(){
        return this.costOfScheduled() - this.costOfPerformed();
    }

    public int calculateDuration(){
        int duration = 0;
        if(startWeek < endWeek){
            duration = endWeek - startWeek;
        } else if(startWeek > endWeek){
            duration = (endWeek - startWeek) + 52;
        }
        return duration;
    }



    public void setEndWeek(int endWeek) {
        this.endWeek = endWeek;
    }
    public void setStartWeek(int startWeek){
        this.startWeek = startWeek;
    }
    public void setProjectName(String projectName){
        this.projectName = projectName;
    }
    public int getProjectDuration(){
        return projectDuration;
    }
    public int getStartWeek(){
        return startWeek;
    }
    public int getEndWeek(){
        return endWeek;
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
}