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
        int longestNameLength = 0;

        for (Risk risk : riskMatrix) {
            if (risk.getName().length() > longestNameLength) {
                longestNameLength = risk.getName().length();
            }
        }

        String header = "Risk";
        header += new String(new char[longestNameLength]).replace("\0", " ");
        header += "Probability     Severity     Impact";

        String astericks = new String(new char[longestNameLength + 15 + 11 + 8 + 6]).replace("\0", "*");

        System.out.println("\n" + astericks);
        System.out.println(header);
        System.out.println(astericks);
        for (Risk risk : riskMatrix) {
            String row = risk.getName();
            row += new String(new char[(4 + longestNameLength) - row.length()]).replace("\0"," ");
            row += risk.getProbability() + "               " + risk.getSeverity() + "            " + risk.getImpact();

            System.out.println(row);
        }
    }
    public static void printHandleTeamMembersSubMenu(){
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
    public static void printHandleTasksSubMenu(){
        System.out.println("1. Add a task");
        System.out.println("2. Add task contribution");
        System.out.println("3. Print all tasks");
        System.out.println("4. Print all unfinished tasks");
    }

    public static void printProjectProgressSubMenu() {
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

    public static void waitForKeyPress() {
        System.out.println("\n(Press enter to continue)");
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

	public static void printTaskSchedule(Task task) {
		System.out.println("\n");
        System.out.println(task.getName() +
		"			w:" + task.getStartWeek() + "		w:" + task.getEndWeek());
    }
   
	public static void printSchedule(List<Task> tasks) {
        System.out.println("\n");
        System.out.println("Task       Start Week          End Week");
        for (Task task : tasks) {
			printTaskSchedule(task);
        }
    }

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

	public static void printDuration(Project project){
		System.out.println("\n");
		System.out.println("***********************");
		System.out.println("*Project Duration: " + project.calculateDuration() + " *");
		System.out.println("***********************");
	}
}
