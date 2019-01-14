import entities.Task;
import utils.*;
import entities.Project;
import java.util.Scanner;


public class ProjectManager {
    private static final int HANDLE_PROJECT = 1;
    private static final int HANDLE_MEMBERS = 2;
    private static final int HANDLE_TASKS = 3;
    private static final int HANDLE_RISKS = 4;
    private static final int QUIT = 10;
    private static final int QUIT_AND_SAVE = 11;

    public static void main(String[] args) throws Exception {
        if (args.length > 0) {
            Parser.setPathToJsonFile(args[0]);
        }
        Project project = Parser.loadData();

        Scanner scanner = new Scanner(System.in);
        int chosenOption;
        do {
            Output.printMenu();
            chosenOption = scanner.nextInt();

            switch (chosenOption) {


                case HANDLE_PROJECT:
                    scanner.nextLine();
                    Output.printProjectOptions();
                    chosenOption=scanner.nextInt();
                    if(chosenOption==1){
                        Output.printRiskMatrix(project.riskMatrix);
                        Output.waitForKeyPress();
                    }else if(chosenOption==2){
                        //project progress
                        System.out.println("project progress");
                        Output.printProgress(project);
                        Output.waitForKeyPress();
                    }else if(chosenOption==3){
                        //project schedule
                        System.out.println("project schedule");
                        Output.printSchedule(project.tasks);
                        Output.waitForKeyPress();
                    }else{
                        Output.incorrectInput();
                    }
                    break;
                case HANDLE_MEMBERS:
                    Output.printAllMembers(project.members);
                    Output.printHandleTeamMembersSubMenu();
                    int option = scanner.nextInt();

                    if(option==1){
                        scanner.nextLine();
                        System.out.println("Enter name");
                        String name = scanner.nextLine();
                        System.out.println("Enter id");
                        String id = scanner.nextLine();
                        project.addMember(name, id);

                    }else if(option==2){
                        scanner.nextLine();
                        System.out.println("Enter id");
                        String id = scanner.nextLine();
                        System.out.println(project.findMember(id));
                        System.out.println("Contributions");
                        project.printAllContributions(id);
                        //total worked time
                        System.out.println(project.totalTimeWorked(id)+" hours total work time.");


                    }else if(option==3){
                        scanner.nextLine();
                        System.out.println("Enter id");
                        String id = scanner.nextLine();
                        project.removeMember(id);
                    }else if(option==4) {
                        Output.printAllMembers(project.members);
                    }else if (option!=10) {
                        Output.incorrectInput();
                    }

                    break;
                case HANDLE_TASKS:
                    Output.printTasks(project.tasks, false);
                    Output.printHandleTasksSubMenu();
                    option = scanner.nextInt();
                    if(option==1){
                        //add task
                        scanner.nextLine();
                        System.out.println("Enter name of task");
                        String name = scanner.nextLine();
                        System.out.println("Enter the task's budgeted hours");
                        int budgetedHours = scanner.nextInt();
                        System.out.println("Enter the start week of the task");
                        int startWeek = scanner.nextInt();
                        System.out.println("Enter the end week of the task");
                        int endWeek = scanner.nextInt();
                        project.addTask(name, budgetedHours, startWeek, endWeek);
                    }else if(option==2){
                        scanner.nextLine();
                        Output.printTasks(project.tasks, false);
                        System.out.println("Choose task number");
                        chosenOption = scanner.nextInt();
                        Task chosenTask = project.tasks.get(chosenOption);
                        scanner.nextLine();
                        System.out.println("Enter id");
                        String id = scanner.nextLine();
                        System.out.println("Enter time");
                        int time = scanner.nextInt();
                        System.out.println("Enter percentage");
                        int percentageCompleted = scanner.nextInt();
                        System.out.println("Enter week");
                        int week = scanner.nextInt();
                        chosenTask.addContribution(id,time,percentageCompleted,week);
                        System.out.println(chosenTask);
                    }else if(option==3){
                        Output.printTasks(project.tasks, false);
                    }else if(option==4){
                        Output.printTasks(project.tasks, true);
                    }else if(option==5){
                        scanner.nextLine();
                        Output.printTasks(project.tasks, false);
                        System.out.println("\n");
                        System.out.println("Pick task number");
                        option=scanner.nextInt();
                        project.tasks.remove(option - 1);
                    }else if (option!=10) {
                        Output.incorrectInput();
                    }
                    break;

                case HANDLE_RISKS:
                    Output.printRiskMatrix(project.riskMatrix);
                    Output.printRiskMenu();
                    option = scanner.nextInt();
                    if(option==1){
                        //add risk
                        scanner.nextLine();
                        Output.riskName();
                        String name = scanner.nextLine();
                        Output.riskSeverity();
                        int severity = scanner.nextInt();
                        Output.riskProbability();
                        int probability = scanner.nextInt();
                        project.addRisk(name,severity,probability);

                    }else if(option==2){
                        //remove risk
                        scanner.nextLine();
                        Output.printRiskMatrix(project.riskMatrix);
                        System.out.println("\n");
                        System.out.println("Pick risk number.");
                        option = scanner.nextInt();
                        project.riskMatrix.remove(option - 1);
                    }else if (option!= 10){
                        Output.incorrectInput();
                    }
                    break;

                case QUIT:
                    Output.printGoodBye();
                    System.exit(0);
                    break;

                case QUIT_AND_SAVE:
                    Parser.saveJson(project);
                    Output.printGoodBye();
                    System.exit(0);
            }
        } while (chosenOption != QUIT);
    }
}