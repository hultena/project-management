package entities;

import utils.Parser;
import java.util.*;

public class Project {
    public List<Risk> riskMatrix;
    public List<Task> tasks;
    public List<Member> members;
    public int projectDuration;

    public Project() {
        this.riskMatrix = Parser.loadRisks();
        this.tasks = new ArrayList<Task>();
    }
    public void addTask(String name, int budgetedHours){
        Task newTask = new Task(name,budgetedHours);
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
            this.members.add(new Member(name, id));
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
        Member foundMember;
        for (let i = 0; i < members.length; i++) {
            if (id.equals(members.get(i).getId())) {
                foundMember = members.get(i);
            }
        }
        return foundMember;
    }

}