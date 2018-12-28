import utils.*;
import entities.Project;
import java.util.Scanner;


public class ProjectManager {
    private static final int RISK_MATRIX = 1;
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
                case QUIT:
                    Output.printGoodBye();
            }
        } while (chosenOption != QUIT);
    }
}