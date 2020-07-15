package models;

public class Ranger {

    private int id;
    private String name;
    private int tag;

    public Ranger(String name,int tag){
        this.name = name;
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getTag() {
        return tag;
    }

}
