package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import utils.Parser;
import java.util.*;
@JsonIgnoreProperties(ignoreUnknown = true)

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
    public void addTask(String name, int budgetedHours){
        Task newTask = new Task(name, budgetedHours);

        tasks.add(newTask);
    }

    public void setProjectDuration(int projectDurationWeeks) {
        this.projectDuration = projectDurationWeeks;
    }

    public List<Member> getMembers() {
        return this.members;
    }

    public void addMember(String name, String id) {
        if (this.findMember(id) == null) {
            this.members.add(new Member());
        } else {
            System.out.println("A member already exists with the ID: " + id);
        }
    }

    public void removeMember(String id) {
        Member memberToRemove = this.findMember(id);
        if (memberToRemove != null) {
            this.members.remove(memberToRemove);
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