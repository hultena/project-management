package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import entities.Project;
import java.util.*;

public class Parser {

    private static String FILENAME;
    private static JsonNode JSON;

    public static Project loadData() throws Exception {
        // Need this when you run project from IDE
        if (FILENAME == null) {
            FILENAME = "./src/template.json";
            System.out.println("No arguments received, using hardcoded path to json file");
        }

        byte[] jsonData = Files.readAllBytes(Paths.get(FILENAME));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        JSON = objectMapper.readTree(jsonData);

        return objectMapper.readValue(JSON.toString(), Project.class);
    }

    public static List<String> getProjects() {
        File folder = new File("./.projects");
        File[] listOfFiles = folder.listFiles();
        List<String> fileNames = new ArrayList<>();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                fileNames.add(listOfFiles[i].getName());
            }
        }
        return fileNames;
    }

    public static void setPathToJsonFile(String name) {
        String workingDir = System.getProperty("user.dir");
        FILENAME = workingDir + "/" + name;
    }
    public JsonNode getJson() {
        return JSON;
    }
    public static void saveJson(Project project){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        try{
            if (FILENAME == null) {
                FILENAME = "./src/template.json";}
            objectWriter.writeValue(new File(FILENAME), project);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}