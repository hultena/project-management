import com.fasterxml.jackson.databind.ObjectMapper;
import utils.Parser;
import entities.Project;

public class ProjectManager {

    public static void main(String[] args) throws Exception {
        if (args.length > 0) {
            Parser.setPathToJsonFile(args[0]);
        }
        Project project = Parser.loadData();

        System.out.println(project.getMembers());
        System.out.println(project.projectDuration);
        System.out.println(project.getProjectName());
        System.out.println(project.getEngineerSalary());
    }
}