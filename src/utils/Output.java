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

        String asterisks = new String(new char[longestNameLength + 15 + 11 + 8 + 6]).replace("\0", "*");

        System.out.println("\n" + asterisks);
        System.out.println(header);
        System.out.println(asterisks);
        int index = 0;
        for (Risk risk : riskMatrix) {
            String row = risk.getName();
            row += new String(new char[(4 + longestNameLength) - row.length()]).replace("\0"," ");
            row += risk.getProbability() + "               " + risk.getSeverity() + "            " + risk.getImpact();

            System.out.println(index+": "+row);
            index++;
        }
    }
    public static void printHandleTeamMembersSubMenu(){
        System.out.println("1. Add team member");
        System.out.println("2. View team member");
        System.out.println("3. Remove team member");
        System.out.println("4. View all team members");
    }

    public static void printAllMembers(List<Member> members){
        int longestMemberName = 0;

        for(Member member : members){
            if (member.getName().length() > longestMemberName) {
                longestMemberName = member.getName().length();
            }
        }
        String asterisks = new String(new char[longestMemberName + 7]).replace("\0", "*");
        System.out.println("\n" + asterisks);
        System.out.println("ID     Name");
        System.out.println(asterisks);

        for (Member member: members) {
            String row = member.getId();
            row += "   " + member.getName();
            System.out.println(row);
        }
    }
    public static void printHandleTasksSubMenu(){
        System.out.println("1. Add a task");
        System.out.println("2. Add task contribution");
        System.out.println("3. Print all tasks");
        System.out.println("4. Print all unfinished tasks");
        System.out.println("5. Remove tasks");
    }

    public static void printProjectProgressSubMenu() {
        System.out.println("1. Duration");
        System.out.println("2. Progress");
        System.out.println("3. Schedule");
    }

    public static void printTasks(List<Task> tasks, boolean onlyUnfinishedTasks){
        int longestNameLength = 0;

        for (Task task : tasks) {
            if (onlyUnfinishedTasks && task.getCompletion() < 100) {
                if (task.getName().length() > longestNameLength) {
                    longestNameLength = task.getName().length();
                }
            } else if (!onlyUnfinishedTasks){
                if (task.getName().length() > longestNameLength) {
                    longestNameLength = task.getName().length();
                }
            }
        }

        String header = "Name";
        header += new String(new char[longestNameLength]).replace("\0", " ");
        header += "Budgeted hours     Start week     End week     Completion(%)     Hours spent";

        String asterisks = new String(new char[longestNameLength + 80]).replace("\0", "*");

        System.out.println("\n" + asterisks);
        System.out.println(header);
        System.out.println(asterisks);
        int index = 0;
        for (Task task : tasks) {
            boolean shouldIncludetask = true;

            if (onlyUnfinishedTasks && task.getCompletion() > 100) {
                shouldIncludetask = false;
            }

            if (shouldIncludetask) {
                String row = task.getName();

                row += new String(new char[(4 + longestNameLength) - row.length()]).replace("\0"," ");
                row += task.getBudgetedHours();
                row += new String(new char[(longestNameLength + 23) - row.length()]).replace("\0", " ") + task.getStartWeek();
                row += new String(new char[(longestNameLength + 23 + 15) - row.length()]).replace("\0", " ") + task.getEndWeek();
                row += new String(new char[(longestNameLength + 23 + 15 + 13) - row.length()]).replace("\0", " ") + task.getCompletion();
                row += new String(new char[(longestNameLength + 23 + 15 + 13 + 18) - row.length()]).replace("\0", " ") + task.getTimeSpent();

                System.out.println(index+": "+row);
            }
            index++;
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
        System.out.println("5.  Handle risks");
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
    public static void printRiskMenu(){
        System.out.println("\n");
        System.out.println("*** Handle risks ***");
        System.out.println("1. Add risk");
        System.out.println("2. Remove risk");


    }
    public static void riskName(){
        System.out.println("Please enter risk name.");
    }
    public static void riskSeverity(){
        System.out.println("Please enter risk severity.");
    }
    public static void riskProbability(){
        System.out.println("Please enter risk probability.");
    }
}
