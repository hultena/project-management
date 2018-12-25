package utils;

<<<<<<< HEAD

import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import entities.Risk;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
=======
import java.nio.file.Files;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
>>>>>>> develop

public class Parser {

    private String filename;
    private JsonNode data;
<<<<<<< HEAD

    public void loadData() throws Exception {
        this.filename = "/home/maikzy/gitreps/project-management/template.json";
        byte[] jsonData = Files.readAllBytes(Paths.get(filename));
        ObjectMapper objectMapper = new ObjectMapper();
        this.data = objectMapper.readTree(jsonData);
        System.out.println(this.data.getClass().getName());

    }

    public void setFilename(String name) {
        this.filename = name;
=======
    private String json;

    public void loadData() throws Exception {
        // Need this when you run project from IDE
        if (this.filename == null) {
            this.filename = "/Users/bartek/gitreps/project-management/src/template.json";
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
>>>>>>> develop
    }
}