package utils;

import java.util.*;

import entities.Member;
import entities.Risk;
import entities.Task;
import entities.Project;

public class Output {

    static Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }

    public static void printRiskMatrix(List<Risk> riskMatrix) {
        System.out.println("\n");
        System.out.println("Risk       Probability          Severity        Impact");
        for (Risk risk : riskMatrix) {
            System.out.println(risk);
        }
    }
    public static void printHandleTeamMembers(){
        System.out.println("1. Add team member");
        System.out.println("2. View team member");
        System.out.println("3. Remove team member");
        System.out.println("4. View all team members");
    }
    public static void memberName(){
        System.out.println("Enter name");
    }
    public static void memberId(){
        System.out.println("Enter id");
    }
    public static void printAllMembers(List<Member> members){
        for(Member member : members){
            System.out.println(member);
        }
    }
    public static void printHandleTasks(){
        System.out.println("1. Add a task");
        System.out.println("2. Add task contribution");
        System.out.println("3. Print all tasks");
        System.out.println("4. Print all unfinished tasks");
    }

    public static void printProjectProgress() {
        System.out.println("1. Duration");
        System.out.println("2. Progress");
        System.out.println("3. Schedule");
    }
    public static void taskName(){
        System.out.println("Enter name of task");
    }
    public static void taskBudgetedHours(){
        System.out.println("Enter the task's budgeted hours");
    }
    public static void taskStartWeek(){
        System.out.println("Enter the start week of the task");
    }
    public static void taskEndWeek(){
        System.out.println("Enter the end week of the task");
    }
    public static void printAllTasks(List<Task> tasks){
        int i = 0;
        for(Task task : tasks){
            System.out.println(i+": "+task);
            i++;
        }
    }
    public static void printAllUncompletedTasks(List<Task> tasks){
        for (Task task : tasks){
            if(task.getCompletion()<100){
                System.out.println(task);
            }
        }
    }
    public static void incorrectInput(){
        System.out.println("Incorrect input");
    }

    public static void waitForAnyKey() {
        System.out.println("\n(Press enter to continue)");
        /* This is to prevent the menu printing before
        the user has had the time to read the risk matrix
         */
        scanner.nextLine();
    }

    public static void printGoodBye() {
        System.out.println("\n");
        System.out.println("Thank you for using our software");
        System.out.println("Bye!");
        System.exit(0);
    }
    public static void printMenu() {
        System.out.println("\n");
        System.out.println("*** Project-management ***");
        System.out.println("1.  Project risk matrix");
        System.out.println("2.  Handle team members");
        System.out.println("3.  Handle tasks");
        System.out.println("4.  Project progress");
        System.out.println("10. Quit");
    }

	//Printing for the schedule
	public static void printTaskSchedule(Task task) {
		System.out.println("\n");
        System.out.println(task.getName() +
		"			w:" + task.getStartWeek() + "		w:" + task.getEndWeek());
    }
   

	//Printing for the schedule
	public static void printSchedule(List<Task> tasks) {
        System.out.println("\n");
        System.out.println("Task       Start Week          End Week");
        for (Task task : tasks) {
			printTaskSchedule(task);
        }
    }

	//Printing EV, CV, and SV (Project progress).

	public static void printProgress(Project project){
		System.out.println("\n");
		System.out.println("*****************************************");
		System.out.println("*Earned Value: " + project.calculateEV() + " *");
		System.out.println("*****************************************");
		System.out.println("*Schedule Variance: " + project.calculateSV()	+ " *");
		System.out.println("*****************************************");
		System.out.println("*Cost Variance: " + project.calculateCV() + " *");
		System.out.println("*****************************************");
	}

	//Printing the project duration.
	public static void printDuration(Project project){
		System.out.println("\n");
		System.out.println("***********************");
		System.out.println("*Project Duration: " + project.calculateDuration() + " *");
		System.out.println("***********************");
	}
}
