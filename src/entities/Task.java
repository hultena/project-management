package entities;

public class Task {
    private String name;
    private int budgetedHours;
    
    public Task(String name, int budgetedHours) {
        this.name = name;
        this.budgetedHours = budgetedHours;
    }

    public String getName() {
        return this.name;
    }
    public int getBudgetedHours() {
        return this.budgetedHours;
    }
}