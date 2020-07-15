package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class AnimalTest {

    @Test
    public void instantiatesCorrectly_true(){
        Animal animal = new Animal("owl");
        assertEquals(true, animal instanceof Animal);
    }



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