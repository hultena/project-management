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
    private static String WORKING_DIRECTORY;

    public static Project loadData(String fileName) throws Exception {
        FILENAME = fileName;

        byte[] jsonData = Files.readAllBytes(Paths.get(Parser.getPathToJsonFolder() + FILENAME));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        JSON = objectMapper.readTree(jsonData);

        return objectMapper.readValue(JSON.toString(), Project.class);
    }

    public static void setWorkingDirectory() {
        WORKING_DIRECTORY = System.getProperty("user.dir");
    }

    public static List<String> getProjects() {
        System.out.println(WORKING_DIRECTORY);
        File folder = new File(getPathToJsonFolder());
        File[] listOfFiles = folder.listFiles();
        List<String> fileNames = new ArrayList<>();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                fileNames.add(listOfFiles[i].getName());
            }
        }
        return fileNames;
    }

    public static String getPathToJsonFolder() {
        String path = WORKING_DIRECTORY;

        System.out.println(path.substring(path.length()-1, path.length()));

        if (path.substring(path.length()-1, path.length()).equals("c")) {
            path += "/.projects/";
        } else {
            path += "/src/.projects/";
        }
        System.out.println(path);
        return path;
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