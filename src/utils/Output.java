package utils;

import java.util.*;
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

    public static void waitForAnyKey() {
        System.out.println("\n(Press any key to continue)");
        /* This is to prevent the menu printing before
        the user has had the time to read the risk matrix
         */
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
        System.out.println("10. Quit");
    }
}
