package entities;

import utils.Parser;
import java.util.*;

public class Project {
    public List<Risk> riskMatrix;
    public List<Task> tasks;

    public Project() {
        this.riskMatrix = Parser.loadRisks();
        this.tasks = new ArrayList<Task>();
    }
    public void addTask(String name, int budgetedHours){
        Task newTask = new Task(name,budgetedHours);
    }

}