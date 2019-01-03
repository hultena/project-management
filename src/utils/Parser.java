package utils;


import java.nio.file.Files;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Parser {

    private String filename;
    private JsonNode data;

    private String json;

    public void loadData() throws Exception {
        // Need this when you run project from IDE
        if (this.filename == null) {
            this.filename = "/home/maikzygit /gitreps/project-management/src/template.json";
            System.out.println("No arguments received, using hardcoded path to json file");
        }

        byte[] jsonData = Files.readAllBytes(Paths.get(filename));
        ObjectMapper objectMapper = new ObjectMapper();
        this.data = objectMapper.readTree(jsonData);
        Object json = objectMapper.readValue(data.toString(), Object.class);
        this.json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);

    }

    public void setPathToJsonFile(String name) {
        String workingDir = System.getProperty("user.dir");
        this.filename = workingDir + "/" + name;
    }
    public String getJson(){
        return json;

    }
}