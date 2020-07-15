package models;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void Location_instantiatesCorrectly_true(){
        Location location = new Location("Donson");
        assertEquals(true, location instanceof Location);
    }
    @Test
    public void Location_instantiatesWithName_String() {
        Location location = new Location("Donson");
        assertEquals("Donson", location.getName());
    }




}