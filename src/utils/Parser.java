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

    public static Project loadData(String fileName) throws Exception {
        FILENAME = fileName;
        System.out.println(FILENAME);

        byte[] jsonData = Files.readAllBytes(Paths.get(Parser.getPathToJsonFile(FILENAME)));
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

    public static String getPathToJsonFile(String name) {
        String workingDir = System.getProperty("user.dir");

        return workingDir + "/.projects/" + name;
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
                FILENAME = "./src/.projects/"+project.getProjectName()+".json";}
            objectWriter.writeValue(new File(FILENAME), project);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}