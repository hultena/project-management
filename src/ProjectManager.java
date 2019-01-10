import entities.Task;
import utils.*;
import entities.Project;
import java.util.Scanner;


public class ProjectManager {
    private static final int RISK_MATRIX = 1;
    private static final int HANDLE_MEMBERS = 2;
    private static final int HANDLE_TASKS = 3;
	private static final int PROJECT_PROGRESS = 4;
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
                    Output.waitForAnyKey();
                    break;
                case HANDLE_MEMBERS:
                    Output.printHandleTeamMembers();
                    chosenOption = scanner.nextInt();

                    if(chosenOption==1){
                        scanner.nextLine();
                        Output.memberName();
                        String name = scanner.nextLine();
                        Output.memberId();
                        String id = scanner.nextLine();
                        project.addMember(name, id);
                        Output.waitForAnyKey();

                    }else if(chosenOption==2){
                        scanner.nextLine();
                        Output.memberId();
                        String id = scanner.nextLine();
                        System.out.println(project.findMember(id));
                        Output.waitForAnyKey();

                    }else if(chosenOption==3){
                        scanner.nextLine();
                        Output.memberId();
                        String id = scanner.nextLine();
                        project.removeMember(id);
                        Output.waitForAnyKey();

                    }else if(chosenOption==4){
                        Output.printAllMembers(project.members);
                        Output.waitForAnyKey();
                    }else{
                        Output.incorrectInput();
                        Output.waitForAnyKey();
                    }
                    break;
                case HANDLE_TASKS:
                    Output.printHandleTasks();
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
                        Output.waitForAnyKey();
                    }else if(chosenOption==2){
                        //TODO: task contribution
                        scanner.nextLine();
                        Output.printAllTasks(project.tasks);
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
                        Output.waitForAnyKey();

                    }else if(chosenOption==3){
                        Output.printAllTasks(project.tasks);
                        Output.waitForAnyKey();
                    }else if(chosenOption==4){
                        //show uncompleted tasks
                        Output.printAllUncompletedTasks(project.tasks);
                        Output.waitForAnyKey();
                    }else{
                        Output.incorrectInput();
                        Output.waitForAnyKey();
                    }
                    break;

				case PROJECT_PROGRESS:
				    Output.printProjectProgress();

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

                    Output.waitForAnyKey();
                    break;

                case QUIT:
                    Output.printGoodBye();
            }
        } while (chosenOption != QUIT);
    }
}