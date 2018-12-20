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
        Contribution i = findContribution(id);
        if(i!=null&&i.getId().equalsIgnoreCase(id)){
            i.addTime(time);
        }
        else{
            Contribution newContribution = new Contribution(id, time);
            contributions.add(newContribution);
        }
    }
    public List<Contribution> getContributions(){
        return contributions;
    }
    public Contribution findContribution(String id) {

        for (Contribution i : contributions) {
            if (i.getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return null;
    }

}