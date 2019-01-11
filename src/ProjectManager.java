import entities.Task;
import utils.*;
import entities.Project;
import java.util.Scanner;


public class ProjectManager {
    private static final int RISK_MATRIX = 1;
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


                case RISK_MATRIX:
                    Output.printRiskMatrix(project.riskMatrix);
                    Output.waitForKeyPress();
                    break;
                case HANDLE_MEMBERS:
                    Output.printHandleTeamMembersSubMenu();
                    chosenOption = scanner.nextInt();

                    if(chosenOption==1){
                        scanner.nextLine();
                        Output.memberName();
                        String name = scanner.nextLine();
                        Output.memberId();
                        String id = scanner.nextLine();
                        project.addMember(name, id);
                    }else if(chosenOption==2){
                        scanner.nextLine();
                        Output.memberId();
                        String id = scanner.nextLine();
                        System.out.println(project.findMember(id));
                    }else if(chosenOption==3){
                        scanner.nextLine();
                        Output.memberId();
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
                        Output.taskName();
                        String name = scanner.nextLine();
                        Output.taskBudgetedHours();
                        int budgetedHours = scanner.nextInt();
                        Output.taskStartWeek();
                        int startWeek = scanner.nextInt();
                        Output.taskEndWeek();
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
                        Output.memberId();
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

                    }
                    break;

                case QUIT:
                    Output.printGoodBye();
            }
        } while (chosenOption != QUIT);
    }
}