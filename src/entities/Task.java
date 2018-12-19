package entities;
import java.util.*;

public class Task {
    private String name;
    private int budgetedHours;
    private List<Contribution> contributions;

    public Task(String name, int budgetedHours) {
        this.name = name;
        this.budgetedHours = budgetedHours;
        contributions = new ArrayList<Contribution>();
    }

    public String getName() {
        return this.name;
    }
    public int getBudgetedHours() {
        return this.budgetedHours;
    }
    public void addContribution(String id, int time){
        Contribution newContribution = new Contribution(id, time);
        contributions.add(newContribution);
    }
    public List<Contribution> getContributions(){
        return contributions;
    }
}