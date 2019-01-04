package utils;

import java.util.*;

import entities.Member;
import entities.Risk;

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
        System.out.println("10. Quit");
    }
}
