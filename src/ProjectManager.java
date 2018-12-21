import com.fasterxml.jackson.databind.ObjectMapper;
import utils.Parser;
import entities.Project;

public class ProjectManager {

    public static void main(String[] args) throws Exception {

        Parser parser = new Parser();
        // Following block should give the Parser the path of the json file
        if (args.length > 0) { // prevents crash when executed without command line arguments
            parser.setFilename(args[0]);
        }
        parser.loadData();
        String json2 = parser.getJson();
        ObjectMapper mp = new ObjectMapper();

        Project p1 = mp.readValue(json2, Project.class);
        System.out.println(p1.getEndWeek());
        System.out.println(p1.engineerSalary);
        System.out.println(p1.riskMatrix.toString());
        System.out.println(p1.members.toString());
        System.out.println(p1.tasks.get(1).getContributions());



    }
}