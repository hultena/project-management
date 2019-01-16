package utils;

import java.time.LocalDate;
import java.util.*;
import com.diogonunes.jcdp.color.*;
import com.diogonunes.jcdp.color.api.Ansi;
import entities.*;

public class Output {

    static Scanner scanner;
    static ColoredPrinter colorPrinter;

    static {
        scanner = new Scanner(System.in);
        colorPrinter = new ColoredPrinter.Builder(1, false).foreground(Ansi.FColor.WHITE).background(Ansi.BColor.BLUE).build();
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
        int index = 1;
        for (Risk risk : riskMatrix) {
            String row = risk.getName();
            row += new String(new char[(4 + longestNameLength) - row.length()]).replace("\0"," ");
            row += risk.getProbability() + "               " + risk.getSeverity() + "            " + risk.impact();

            System.out.println(index+": "+row);
            index++;
        }
    }
    public static void printHandleTeamMembersSubMenu(){
        System.out.println("\n*** Manage members ***");
        System.out.println("1.  Add team member");
        System.out.println("2.  View team member");
        System.out.println("3.  Remove team member");
        System.out.println("4.  View all team members");
        System.out.println("10. Back to menu");
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
        System.out.println("\n*** Manage tasks ***");
        System.out.println("1.  Add task");
        System.out.println("2.  Add task contribution");
        System.out.println("3.  Print all tasks");
        System.out.println("4.  Print all unfinished tasks");
        System.out.println("5.  Remove tasks");
        System.out.println("10. Back to menu");
    }

    public static void printTasks(List<Task> tasks, boolean onlyUnfinishedTasks){
        int longestNameLength = 0;

        for (Task task : tasks) {
            if (onlyUnfinishedTasks && task.completion() < 100) {
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
        int index = 1;
        for (Task task : tasks) {
            boolean shouldIncludeTask = true;

            if (onlyUnfinishedTasks && task.completion() > 100) {
                shouldIncludeTask = false;
            }

            if (shouldIncludeTask) {
                String row = task.getName();

                row += new String(new char[(4 + longestNameLength) - row.length()]).replace("\0"," ");
                row += task.getBudgetedHours();
                row += new String(new char[(longestNameLength + 23) - row.length()]).replace("\0", " ") + task.getStartDate();
                row += new String(new char[(longestNameLength + 23 + 15) - row.length()]).replace("\0", " ") + task.getEndDate();
                row += new String(new char[(longestNameLength + 23 + 15 + 13) - row.length()]).replace("\0", " ") + task.completion();
                row += new String(new char[(longestNameLength + 23 + 15 + 13 + 18) - row.length()]).replace("\0", " ") + task.timeSpent(LocalDate.now());

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
    }
    public static void printMenu() {
        System.out.println("\n");

        colorPrinter.println("************************************", Ansi.Attribute.BOLD, Ansi.FColor.GREEN, Ansi.BColor.NONE);
        colorPrinter.println("***   Project-management v1.0   ****", Ansi.Attribute.BOLD, Ansi.FColor.GREEN, Ansi.BColor.NONE);
        colorPrinter.println("************************************", Ansi.Attribute.BOLD, Ansi.FColor.GREEN, Ansi.BColor.NONE);
        System.out.println("1.  Manage project");
        System.out.println("2.  Manage team members");
        System.out.println("3.  Manage tasks");
        System.out.println("4.  Manage risks");
        System.out.println("10. Quit without saving");
        System.out.println("11. Quit and save");
        colorPrinter.clear();
    }

    public static void printTaskSchedule(Task task) {
        System.out.println("\n");
        System.out.println(task.getName() +
                "			w:" + task.getStartDate() + "		w:" + task.getEndDate());
    }

    public static void printSchedule(List<Task> tasks) {
        System.out.println("\n");
        System.out.println("Task       Start Week          End Week");
        for (Task task : tasks) {
            printTaskSchedule(task);
        }
    }

    public static void printProgress(Project project, LocalDate date){
        System.out.println("\n");
        System.out.println("*****************************************");
        System.out.println("*Earned Value: " + project.calculateEV(date) + " *");
        System.out.println("*****************************************");
        System.out.println("*Schedule Variance: " + project.calculateSV(date)	+ " *");
        System.out.println("*****************************************");
        System.out.println("*Cost Variance: " + project.calculateCV(date) + " *");
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
        System.out.println("*** Manage risks ***");
        System.out.println("1.  Add risk");
        System.out.println("2.  Remove risk");
        System.out.println("10. Back to menu");
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
    public static void printProjectOptions(){
        System.out.println("1. Print risk matrix");
        System.out.println("2. Project progress");
        System.out.println("3. Project schedule");
    }

    public static void testPrint(Contribution contribution,Task task){
        System.out.println("Task name: "+task.getName()+" | Week: "+contribution.getDate()+" | Time spent: "+contribution.getTimeSpent()+" hours. | Percentage completed: "+contribution.getPercentageCompleted()+"%");
    }
}
