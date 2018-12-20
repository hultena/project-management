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
    public static String filename;

    public static void loadJSON() throws Exception {
        filename = "/home/maikzy/gitreps/project-management/template.json";
        byte[] jsonData = Files.readAllBytes(Paths.get(filename));
        Map<String,String> myMap = new HashMap<String, String>();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonData);

        System.out.println(rootNode.get("tasks").get(1));
    }

    public static List<Risk> loadRisks() {
        // TODO: Implement real parsing of JSON data
        List<Risk> risks = new ArrayList<Risk>();


        Risk test1 = new Risk("People not showing up to meetings", (float) 0.5, 4);
        risks.add(test1);

        return risks;
    }
}