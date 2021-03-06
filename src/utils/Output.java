package utils;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
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

    public static void printAllProjects(List<String> fileNames) {
        System.out.println("\n");
        for (int i=1; i <= fileNames.size(); i++) {
            String file = fileNames.get(i-1);
            System.out.println(i + ". " + file.substring(0, file.length() - 5));
        }
    }

    public static void printIntroMenu() {
        colorPrinter.println("\n", Ansi.Attribute.NONE, Ansi.FColor.GREEN, Ansi.BColor.NONE);
        System.out.println("1. Load project");
        System.out.println("2. Create new project");
        colorPrinter.clear();
    }

    public static void printMenu() {
        System.out.println("\n");
        colorPrinter.println("1.  Manage project", Ansi.Attribute.NONE, Ansi.FColor.GREEN, Ansi.BColor.NONE);
        System.out.println("2.  Manage team members");
        System.out.println("3.  Manage tasks");
        System.out.println("4.  Manage risks");
        System.out.println("10. Quit without saving");
        System.out.println("11. Quit and save");
        colorPrinter.clear();
    }
    public static void printManageProjectSubMenu(){
        colorPrinter.println("\n", Ansi.Attribute.NONE, Ansi.FColor.GREEN, Ansi.BColor.NONE);
        System.out.println("1.  Project schedule");
        System.out.println("2.  Project budget");
        System.out.println("10. Back to menu");
        colorPrinter.clear();
    }
    public static void printHandleTeamMembersSubMenu(){
        colorPrinter.println("\n*** Manage members ***", Ansi.Attribute.NONE, Ansi.FColor.GREEN, Ansi.BColor.NONE);
        System.out.println("1.  Add team member");
        System.out.println("2.  View team member");
        System.out.println("3.  Remove team member");
        System.out.println("4.  View all team members");
        System.out.println("10. Back to menu");
        colorPrinter.clear();
    }
    public static void printHandleTasksSubMenu(){
        colorPrinter.println("\n*** Manage tasks ***", Ansi.Attribute.NONE, Ansi.FColor.GREEN, Ansi.BColor.NONE);
        System.out.println("1.  Add task");
        System.out.println("2.  Add task contribution");
        System.out.println("3.  Print all tasks");
        System.out.println("4.  Print all unfinished tasks");
        System.out.println("5.  Remove tasks");
        System.out.println("10. Back to menu");
        colorPrinter.clear();
    }
    public static void printRiskMenu(){
        colorPrinter.println("\n", Ansi.Attribute.NONE, Ansi.FColor.GREEN, Ansi.BColor.NONE);
        System.out.println("*** Manage risks ***");
        System.out.println("1.  Add risk");
        System.out.println("2.  Remove risk");
        System.out.println("10. Back to menu");
        colorPrinter.clear();
    }

    public static void printAllMembers(List<Member> members){
        int longestMemberName = 0;

        for(Member member : members){
            if (member.getName().length() > longestMemberName) {
                longestMemberName = member.getName().length();
            }
        }
        String asterisks = new String(new char[longestMemberName + 7]).replace("\0", "*");
        colorPrinter.println("\n" + asterisks, Ansi.Attribute.BOLD, Ansi.FColor.CYAN, Ansi.BColor.NONE);
        System.out.println("ID     Name");
        System.out.println(asterisks);
        colorPrinter.clear();

        for (Member member: members) {
            String row = member.getId();
            row += "   " + member.getName();
            System.out.println(row);
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
        System.out.println("Thank you for using SoftMan 1.0");
        System.out.println("Bye!");
    }

    public static void printSchedule(List<Task> tasks) {
        int longestNameLength=findLongestTaskName(tasks);
        String asterisks = new String(new char[longestNameLength + 30]).replace("\0", "*");
        String header = "Task name";
        header += new String(new char[longestNameLength]).replace("\0", " ");
        header += "Start week   End week";
        colorPrinter.println("\n", Ansi.Attribute.NONE, Ansi.FColor.CYAN, Ansi.BColor.NONE);
        System.out.println(asterisks);
        System.out.println(header);
        System.out.println(asterisks);
        colorPrinter.clear();
        for (Task task : tasks) {
            String row = task.getName();
            row += new String(new char[(longestNameLength+9) - row.length()]).replace("\0"," ")+convertDateToWeek(task.getStartDate());
            row += new String(new char[(longestNameLength+22) - row.length()]).replace("\0", " ")+convertDateToWeek(task.getEndDate());
            System.out.println(row);
        }
    }
    public static LocalDate printDateChoice(){
        LocalDate date = null;
        System.out.println("What date should the budget be based on?");
        System.out.println("1. Current date (" + LocalDate.now().toString() + ")");
        System.out.println("2. Specify date");
        int chosenOption = scanner.nextInt();
        if(chosenOption==1){
            scanner.nextLine();
            date = LocalDate.now();
        }else if(chosenOption==2){
            scanner.nextLine();
            System.out.println("Enter date as YYYYMMDD");
            String input = scanner.nextLine();
            date = Project.createDate(input);
        }else{
            Output.incorrectInput();
            Output.waitForKeyPress();
        }
        return date;
    }
    public static int convertDateToWeek(LocalDate date){
        TemporalField week = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        return date.get(week);
    }
    public static int findLongestTaskName(List<Task> tasks){
        int longestTaskName=0;
        for (Task task:tasks){
            if(task.getName().length()>longestTaskName){
                longestTaskName=task.getName().length();
            }
        }
        return longestTaskName;
    }

    public static void printProjectInformation(Project project){
        String asterisks = new String(new char[project.getProjectName().length() + 50]).replace("\0", "*");

        colorPrinter.println(asterisks, Ansi.Attribute.NONE, Ansi.FColor.CYAN, Ansi.BColor.NONE);
        System.out.print("Project ");
        colorPrinter.clear();
        System.out.print(project.getProjectName());
        colorPrinter.print(" Start date ", Ansi.Attribute.NONE, Ansi.FColor.CYAN, Ansi.BColor.NONE);
        colorPrinter.clear();
        System.out.print(project.getStartDate());
        colorPrinter.print(" End date ", Ansi.Attribute.NONE, Ansi.FColor.CYAN, Ansi.BColor.NONE);
        colorPrinter.clear();
        System.out.println(project.getEndDate());

        colorPrinter.print(asterisks, Ansi.Attribute.NONE, Ansi.FColor.CYAN, Ansi.BColor.NONE);
    }
    public static void printProjectMetrics(Project project,LocalDate date){
        DecimalFormat decimalFormat = new DecimalFormat("#");
        System.out.println("\nActual Cost:       "+decimalFormat.format(project.costOfPerformed(date)));
        System.out.println("Earned Value:      "+decimalFormat.format(project.calculateEV(date)));
        System.out.println("Cost Variance:     "+decimalFormat.format(project.calculateCV(date)));
        System.out.println("Schedule Variance: "+decimalFormat.format(project.calculateSV(date)));
        System.out.println("Planned Value:     "+decimalFormat.format(project.calculatePV(date)));
    }
    public static void printLogoAndVersion() {
        colorPrinter.println("  ____         __ _   __  __                  _        ___ ", Ansi.Attribute.NONE, Ansi.FColor.MAGENTA, Ansi.BColor.NONE);
        colorPrinter.println(" / ___|  ___  / _| |_|  \\/  | __ _ _ __      / |      / _ \\ ", Ansi.Attribute.NONE, Ansi.FColor.MAGENTA, Ansi.BColor.NONE);
        colorPrinter.println(" \\___ \\ / _ \\| |_| __| |\\/| |/ _` | '_ \\     | |     | | | |", Ansi.Attribute.NONE, Ansi.FColor.MAGENTA, Ansi.BColor.NONE);
        colorPrinter.println("  ___) | (_) |  _| |_| |  | | (_| | | | |    | |  _  | |_| |", Ansi.Attribute.NONE, Ansi.FColor.MAGENTA, Ansi.BColor.NONE);
        colorPrinter.println(" |____/ \\___/|_|  \\__|_|  |_|\\__,_|_| |_|    |_| (_)  \\___/ ", Ansi.Attribute.NONE, Ansi.FColor.MAGENTA, Ansi.BColor.NONE);
        System.out.println("____________________________________________________________");
        System.out.println("              Software project management tool              ");
        colorPrinter.clear();
    }

    public static void printRiskMatrix(List<Risk> riskMatrix) {
        colorPrinter.clear();
        Collections.sort(riskMatrix, new Comparator<Risk>() {
            public int compare(Risk b1, Risk b2) {
                if (b1.impact() > b2.impact()) {
                    return -1;
                } else if(b1.impact() < b2.impact()) {
                    return 1;
                }else {
                    return 0;
                }
            }
        });
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

        colorPrinter.println("\n" + asterisks, Ansi.Attribute.NONE, Ansi.FColor.CYAN, Ansi.BColor.NONE);
        System.out.println(header);
        System.out.println(asterisks);
        int index = 1;
        for (Risk risk : riskMatrix) {
            String riskName =index+": "+risk.getName();
            riskName += new String(new char[(4 + longestNameLength) - riskName.length()]).replace("\0"," ");
            colorPrinter.print(riskName, Ansi.Attribute.NONE, Ansi.FColor.WHITE, Ansi.BColor.NONE);

            String numbers =risk.getProbability() + "               " + risk.getSeverity() + "            " + risk.impact();

            if (risk.impact() < 6) {
                colorPrinter.println(numbers, Ansi.Attribute.NONE, Ansi.FColor.GREEN, Ansi.BColor.NONE);
            }else if (risk.impact() < 10) {
                colorPrinter.println(numbers, Ansi.Attribute.NONE, Ansi.FColor.YELLOW, Ansi.BColor.NONE);
            }else  {
                colorPrinter.println(numbers, Ansi.Attribute.NONE, Ansi.FColor.RED, Ansi.BColor.NONE);
            }

            index++;
        }
        colorPrinter.clear();
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
        colorPrinter.println("\n" + asterisks, Ansi.Attribute.NONE, Ansi.FColor.CYAN, Ansi.BColor.NONE);
        System.out.println(header);
        System.out.println(asterisks);
        colorPrinter.clear();
        int index = 1;
        for (Task task : tasks) {
            boolean shouldIncludeTask = true;

            if (onlyUnfinishedTasks && task.completion() >= 100) {
                shouldIncludeTask = false;
            }

            if (shouldIncludeTask) {
                String row = task.getName();

                row += new String(new char[(4 + longestNameLength) - row.length()]).replace("\0"," ");
                row += task.getBudgetedHours();
                row += new String(new char[(longestNameLength + 23) - row.length()]).replace("\0", " ") + convertDateToWeek(task.getStartDate());
                row += new String(new char[(longestNameLength + 23 + 15) - row.length()]).replace("\0", " ") + convertDateToWeek(task.getEndDate());
                row += new String(new char[(longestNameLength + 23 + 15 + 13) - row.length()]).replace("\0", " ") + task.completion();
                row += new String(new char[(longestNameLength + 23 + 15 + 13 + 18) - row.length()]).replace("\0", " ") + task.timeSpent(LocalDate.now());

                System.out.println(index+": "+row);
            }
            index++;
        }
    }
    public static void printAllContributions(List<Contribution> contributions, List<Task> tasks, String memberName){
        int longestNameLength = findLongestTaskName(tasks);

        String header = "Name";
        header += new String(new char[longestNameLength]).replace("\0", " ");
        header += "   Date of adding    Hours spent    Completion(%)";

        int totalHours = 0;
        for (Contribution cont : contributions) {
            totalHours += cont.getTimeSpent();
        }
        String asterisks = new String(new char[longestNameLength + 80]).replace("\0", "*");
        colorPrinter.println("\n" + memberName + "'s contributions: " + totalHours + " hours total work-time", Ansi.Attribute.NONE, Ansi.FColor.CYAN, Ansi.BColor.NONE);
        System.out.println(asterisks);
        System.out.println(header);
        System.out.println(asterisks);
        colorPrinter.clear();
        int index = 1;

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            Contribution cont = contributions.get(i);

            String row = task.getName();

            row += new String(new char[(4 + longestNameLength) - row.length()]).replace("\0"," ");
            row += cont.getDate();
            row += new String(new char[(longestNameLength + 22) - row.length()]).replace("\0", " ") + cont.getTimeSpent();
            row += new String(new char[(longestNameLength + 22 + 15) - row.length()]).replace("\0", " ") + cont.getPercentageCompleted();

            System.out.println(index+": "+row);
            index++;
        }
    }
}
