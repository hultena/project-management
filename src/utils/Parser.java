package utils;


import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import entities.Risk;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Parser {

    private String filename;
    private JsonNode data;

    public void loadData() throws Exception {
        this.filename = "/home/maikzy/gitreps/project-management/template.json";
        byte[] jsonData = Files.readAllBytes(Paths.get(filename));
        ObjectMapper objectMapper = new ObjectMapper();
        this.data = objectMapper.readTree(jsonData);
        System.out.println(this.data.getClass().getName());

    }

    public void setFilename(String name) {
        this.filename = name;
    }
}