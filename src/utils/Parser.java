package utils;


import java.util.*;
import entities.Risk;
import com.fasterxml.jackson.*;

public class Parser {
    public static String filename;

    public static List<Risk> loadRisks() {
        // TODO: Implement real parsing of JSON data
        List<Risk> risks = new ArrayList<Risk>();


        Risk test1 = new Risk("People not showing up to meetings", (float) 0.5, 4);
        risks.add(test1);

        return risks;
    }
}