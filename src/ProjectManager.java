import entities.Task;
import utils.*;
import entities.Project;
import java.time.LocalDate;
import java.util.Scanner;
import java.io.File;

public class ProjectManager {
    private static final int HANDLE_PROJECT = 1;
    private static final int HANDLE_MEMBERS = 2;
    private static final int HANDLE_TASKS = 3;
    private static final int HANDLE_RISKS = 4;
    private static final int QUIT = 10;
    private static final int QUIT_AND_SAVE = 11;

    public static void main(String[] args) throws Exception {
        Parser.setWorkingDirectory();

        Scanner scanner = new Scanner(System.in);
        int chosenOption;
        Output.printLogoAndVersion();
        Output.printIntroMenu();

        chosenOption=scanner.nextInt();
        Project project = null;

        if(chosenOption==1) {
            Output.printAllProjects(Parser.getProjects());

            int option = scanner.nextInt();

            String chosenProject = Parser.getProjects().get(option - 1);
            project = Parser.loadData(chosenProject);
        }else if(chosenOption==2){
            scanner.nextLine();
            System.out.println("Enter project name");
            String name = scanner.nextLine();
            System.out.println("Enter project start date in the format YYYYMMDD");
            String startDate = scanner.nextLine();
            System.out.println("Enter project end date in the format YYYYMMDD");
            String endDate = scanner.nextLine();
            System.out.println("Enter engineer salary");
            int salary = scanner.nextInt();
            project = new Project(name, Project.createDate(startDate), Project.createDate(endDate), salary);
            Parser.setFILENAME(project.getProjectName() + ".json");
        }

        do {
            Output.printMenu();
            chosenOption = scanner.nextInt();

            switch (chosenOption) {


                case HANDLE_PROJECT:
                    scanner.nextLine();
                    Output.printProjectInformation(project);
                    Output.printManageProjectSubMenu();
                    int option = scanner.nextInt();
                    if(option==1){ // project schedule
                        Output.printSchedule(project.tasks);
                        Output.waitForKeyPress();
                    }else if(option==2) {
                        LocalDate date = Output.printDateChoice();
                        Output.printProjectMetrics(project,date);
                        Output.waitForKeyPress();
                    }else if(option!=10){
                        Output.incorrectInput();
                    }
                    break;
                case HANDLE_MEMBERS:
                    Output.printAllMembers(project.members);
                    Output.printHandleTeamMembersSubMenu();
                    option = scanner.nextInt();

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
                        project.printAllContributionsByMember(id);

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
                        scanner.nextLine();
                        System.out.println("Enter the start date of the task in the format YYYYMMDD");
                        String input = scanner.nextLine();
                        LocalDate startDate = Project.createDate(input);
                        System.out.println("Enter the end date of the task in the format YYYYMMDD");
                        input = scanner.nextLine();
                        LocalDate endDate = Project.createDate(input);
                        project.addTask(name, budgetedHours, startDate, endDate);
                    }else if(option==2){
                        scanner.nextLine();
                        Output.printTasks(project.tasks, false);
                        System.out.println("Choose task number");
                        chosenOption = scanner.nextInt()-1;
                        Task chosenTask = project.tasks.get(chosenOption);
                        scanner.nextLine();
                        System.out.println("Enter task id");
                        String id = scanner.nextLine();
                        System.out.println("Enter time (hours)");
                        int time = scanner.nextInt();
                        System.out.println("Enter percentage completed");
                        int percentageCompleted = scanner.nextInt();
                        LocalDate date = LocalDate.now();
                        chosenTask.addContribution(id,time,percentageCompleted,date);
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
                        System.out.println("Please enter risk name");
                        String name = scanner.nextLine();
                        System.out.println("Please enter risk severity");
                        int severity = scanner.nextInt();
                        System.out.println("Please enter risk probabibility");
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