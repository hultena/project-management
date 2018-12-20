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

        Project project = new Project();



    }
}