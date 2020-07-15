package models;

import interfaces.AnimalInterface;

public class Animal implements AnimalInterface {
    public int id;
    public String name;
    public    String type;
    public static final String ANIMAL_TYPE = "animal";

    public Animal(String name){
        this.name = name;
        this.type = ANIMAL_TYPE;
    }

    @Override
    public void save() {

    }

    @Override
    public void update(String name) {

    }

    @Override
    public void delete() {

    }
}
