package entities;

import utils.Parser;
import java.util.*;

public class Project {
    public List<Risk> riskMatrix;

    public Project() {
        this.riskMatrix = Parser.loadRisks();
    }


}