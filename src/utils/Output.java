package utils;

import java.util.*;

import entities.Member;
import entities.Risk;
import entities.Task;

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
        for(Task task : tasks){
            System.out.println(task);
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
        System.out.println("10. Quit");
    }
}
