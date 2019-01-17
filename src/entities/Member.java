package entities;

public class Member {

    private String name;
    private String id;

    public Member(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public Member() {} // Empty constructor for jackson


    public String getName() {
        return this.name;
    }
    public String getId() {
        return this.id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setId(String id){
        this.id = id;
    }
}