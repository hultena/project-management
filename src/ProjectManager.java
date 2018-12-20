import utils.Parser;

public class ProjectManager {

    public static void main(String[] args) throws Exception {

        // Following block should give the Parser the path of the json file
        if (args.length > 0) { // prevents crash when executed without command line arguments
            Parser.filename = args[0];
        }


    }
}