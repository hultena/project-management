import entities.Task;
import utils.*;
import entities.Project;
import java.util.Scanner;


public class ProjectManager {
    private static final int HANDLE_PROJECT = 1;
    private static final int HANDLE_MEMBERS = 2;
    private static final int HANDLE_TASKS = 3;
    private static final int PROJECT_PROGRESS = 4;
    private static final int HANDLE_RISKS = 5;
    private static final int QUIT = 10;

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
                    }else if(chosenOption==3){
                        //project schedule
                        System.out.println("project schedule");
                    }else{
                        Output.incorrectInput();
                    }
                    break;
                case HANDLE_MEMBERS:
                    Output.printHandleTeamMembersSubMenu();
                    chosenOption = scanner.nextInt();

                    if(chosenOption==1){
                        scanner.nextLine();
                        System.out.println("Enter name");
                        String name = scanner.nextLine();
                        System.out.println("Enter id");
                        String id = scanner.nextLine();
                        project.addMember(name, id);

                    }else if(chosenOption==2){
                        scanner.nextLine();
                        System.out.println("Enter id");
                        String id = scanner.nextLine();
                        System.out.println(project.findMember(id));
                    }else if(chosenOption==3){
                        scanner.nextLine();
                        System.out.println("Enter id");
                        String id = scanner.nextLine();
                        project.removeMember(id);
                    }else if(chosenOption==4){
                        Output.printAllMembers(project.members);
                    }else{
                        Output.incorrectInput();
                    }

                    Output.waitForKeyPress();
                    break;
                case HANDLE_TASKS:
                    Output.printHandleTasksSubMenu();
                    chosenOption = scanner.nextInt();
                    if(chosenOption==1){
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
                    }else if(chosenOption==2){
                        //TODO: task contribution
                        scanner.nextLine();
                        Output.printTasks(project.tasks, false);
                        System.out.println("Choose task number");
                        chosenOption = scanner.nextInt();
                        Task chosenTask = project.tasks.get(chosenOption);
                        scanner.nextLine();
                        System.out.println("Enter id");
                        String id = scanner.nextLine();
                        System.out.println("enter time");
                        int time = scanner.nextInt();
                        System.out.println("enter percentage");
                        int percentageCompleted = scanner.nextInt();
                        chosenTask.addContribution(id,time,percentageCompleted);
                        System.out.println(chosenTask);
                    }else if(chosenOption==3){
                        Output.printTasks(project.tasks, false);
                    }else if(chosenOption==4){
                        Output.printTasks(project.tasks, true);
                    }else if(chosenOption==5){
                        scanner.nextLine();
                        Output.printTasks(project.tasks, false);
                        System.out.println("\n");
                        System.out.println("Pick task number");
                        chosenOption=scanner.nextInt();
                        project.tasks.remove(chosenOption);
                    }else{
                        Output.incorrectInput();
                    }
                    Output.waitForKeyPress();
                    break;

                case PROJECT_PROGRESS:
                    Output.printProjectProgressSubMenu();

                    chosenOption = scanner.nextInt();
                    if (chosenOption==1){
                        Output.printDuration(project);
                    }else if(chosenOption==2){
                        Output.printProgress(project);
                    }else if(chosenOption==3){
                        Output.printSchedule(project.tasks);
                    }else{
                        Output.incorrectInput();
                    }

                    Output.waitForKeyPress();
                    break;

                case HANDLE_RISKS:
                    Output.printRiskMenu();
                    chosenOption=scanner.nextInt();
                    if(chosenOption==1){
                        //add risk
                        scanner.nextLine();
                        Output.riskName();
                        String name = scanner.nextLine();
                        Output.riskSeverity();
                        int severity = scanner.nextInt();
                        Output.riskProbability();
                        int probability = scanner.nextInt();
                        project.addRisk(name,severity,probability);

                    }else if(chosenOption==2){
                        //remove risk
                        scanner.nextLine();
                        Output.printRiskMatrix(project.riskMatrix);
                        System.out.println("\n");
                        System.out.println("Pick risk number.");
                        chosenOption = scanner.nextInt();
                        project.riskMatrix.remove(chosenOption);
                    }else{
                        Output.incorrectInput();
                    }
                    Output.waitForKeyPress();
                    break;

                case QUIT:
                    Parser.saveJson(project);
                    Output.printGoodBye();
            }
        } while (chosenOption != QUIT);
    }
}