package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnimalTest {

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

//    @Test
//    public void Animal_instantiatesCorrectlyWithAnId() {
//        Animal animal = new Animal("owl");
//        animal.save();
//        assertTrue(animal.getId()>0);





    @Test
    public void save() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}