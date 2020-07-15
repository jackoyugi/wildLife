package models;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class RangerTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void Ranger_instantiatesCorrectly_true(){
        Ranger ranger = new Ranger("jackwonder", 89898);
        assertEquals(true, ranger instanceof Ranger);
    }
    @Test
    public void Ranger_instantiatesWithName_String() {
        Ranger ranger = new Ranger("jackwonder", 89898);
        assertEquals("jackwonder", ranger.getName());
    }
    @Test
    public void Ranger_instantiatesWithAnId() {
        Ranger ranger = new Ranger("Donson", 89898);
        ranger.save();
        assertTrue(ranger.getId()>0);
    }


}