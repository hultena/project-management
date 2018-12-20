package entities;

public class Member {
    
    private String name;
    private String id;

    public Member(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    public String getId() {
        return this.id;
    }
}