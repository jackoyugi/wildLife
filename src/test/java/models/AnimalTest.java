package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.sql2o.Sql2o;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnimalTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void instantiatesCorrectly_true(){
        Animal animal = new Animal("owl");
        assertEquals(true, animal instanceof Animal);
    }

    @Test
    public void Animal_instantiatesCorrectlyWithType_String() {
        Animal animal = new Animal("owl");
        assertEquals("animal", animal.getType());
    }
    @Test
    public void Animal_instantiatesWithName_String() {
        Animal animal = new Animal("owl");
        assertEquals("owl", animal.getName());
    }

    @Test
    public void Animal_instantiatesCorrectlyWithAnId() {
        Animal animal = new Animal("owl");
        animal.save();
        assertTrue(animal.getId() > 0);
    }

    @Test
    public void save_CorrectlyIntoTheDatabase() {
        Animal animal = new Animal("owl");
        animal.save();
        assertTrue(Animal.all().get(0).equals(animal));
    }
    @Test
    public void findById_returnsAnimalWIthSameID_secondAnimal(){
        Animal animalOne = new  Animal("owl");
        animalOne.save();
        Animal animalTwo = new Animal("crown");
        animalTwo.save();
        assertEquals(Animal.findById(animalTwo.getId()), animalTwo);
    }
    @Test
    public void equals_returnsTrueIfAnimalsAreSame(){
        Animal animalOne = new Animal("owl");
        Animal animalTwo = new Animal("owl");
        assertTrue(animalOne.equals(animalTwo));
    }
    @Test
    public void save_returnsTrueIfNamesAreTheSame(){
        Animal animal = new Animal("owl");
        animal.save();
        assertEquals(Animal.all().get(0), animal);
    }
    @Test
    public void all_returnsAllInstancesOfAnimals_true(){
        Animal animalOne = new  Animal("owl");
        animalOne.save();
        Animal animalTwo = new Animal("crown");
        animalTwo.save();
        assertEquals(Animal.all().get(0), animalOne);
        assertEquals(Animal.all().get(1), animalTwo);
    }
    @Test
    public void save_assignsIdToObject() {
        Animal animal = new Animal("owl");
        animal.save();
        Animal savedAnimal = Animal.all().get(0);
        assertEquals(animal.getId(), savedAnimal.getId());
    }
    @Test
    public void delete_deletesAnimal_true(){
        Animal animal = new Animal("crown");
        animal.save();
        int animalId = animal.getId();
        animal.delete();
        assertEquals(null, Animal.findById(animalId));
    }

}