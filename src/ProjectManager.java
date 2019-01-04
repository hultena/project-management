import utils.*;
import entities.Project;
import java.util.Scanner;


public class ProjectManager {
    private static final int RISK_MATRIX = 1;
    private static final int HANDLE_MEMBERS = 2;
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
                    int choice = scanner.nextInt();

                    if(choice==1){
                        scanner.nextLine();
                        Output.memberName();
                        String name = scanner.nextLine();
                        Output.memberId();
                        String id = scanner.nextLine();
                        project.addMember(name, id);
                        Output.waitForAnyKey();

                    }else if(choice==2){
                        scanner.nextLine();
                        Output.memberId();
                        String id = scanner.nextLine();
                        System.out.println(project.findMember(id));
                        Output.waitForAnyKey();

                    }else if(choice==3){
                        scanner.nextLine();
                        Output.memberId();
                        String id = scanner.nextLine();
                        project.removeMember(id);
                        Output.waitForAnyKey();

                    }else if(choice==4){
                        Output.printAllMembers(project.members);
                        Output.waitForAnyKey();
                    }else{
                        Output.incorrectInput();
                    }
                    break;
                case QUIT:
                    Output.printGoodBye();
            }
        } while (chosenOption != QUIT);
    }
}