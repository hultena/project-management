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

    public void addTask(String name, int budgetedHours, int startWeek, int endWeek){
        Task newTask = new Task();
        newTask.setName(name);
        newTask.setBudgetedHours(budgetedHours);
        newTask.setStartWeek(startWeek);
        newTask.setEndWeek(endWeek);
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

	/*Calculating the mean percentage of the tasks completed to
	get an estimate for the entire project*/

	public double meanPercentage(){
	double totalPercent = 0;
	int numberOfTasks = 0;
	double meanPercentage = 0;
	for (Task task : tasks) {
			totalPercent = totalPercent + task.getCompletion();
			numberOfTasks += 1;
        }
		if (numberOfTasks != 0) {
		meanPercentage = totalPercent/numberOfTasks;
		}
		return meanPercentage;
	}

	//Calculating the earned value with the mean percentage for the tasks.

	public double calculateEV(){
	double EV = 0;
	double completedPercent = meanPercentage();
	double BAC = costOfPerformed();

	if(BAC != 0){
	EV = completedPercent/BAC;	
	}
		return EV;
	}


	//Calculating the total cost of the work performed for the tasks.

	public double costOfPerformed(){
		int hoursSpent = 0;
		double actualTotalPay = 0;
		for(Task task : tasks) {
			hoursSpent = hoursSpent + task.getTimeSpent();
		}
		actualTotalPay = hoursSpent * engineerSalary;
		return actualTotalPay;
	}

	//Calculating the scheduled total cost of the tasks.

	public double costOfScheduled(){
		double plannedTotalPay = 0;
		int totalHoursPlanned = 0;

		for(Task task: tasks) {
			totalHoursPlanned = totalHoursPlanned + task.getBudgetedHours();
		}
		plannedTotalPay = totalHoursPlanned * engineerSalary;
		return plannedTotalPay;
	}

	//Calculating the schedule variance.

	public double calculateSV(){
		double scheduleVariance = 0;
		double workPerformed = costOfPerformed();
		double workScheduled = costOfScheduled();
		scheduleVariance = workPerformed - workScheduled;
		return scheduleVariance;
	}

	//Calculating the cost variance.

	public double calculateCV(){
		double costVariance = 0;
		double actualCost = costOfPerformed();
		double budgetedCost = costOfScheduled();
		costVariance = budgetedCost - actualCost;
		return costVariance;
	}

	//Calculating the project duration.

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