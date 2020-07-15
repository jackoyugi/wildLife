package models;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class EndangeredAnimalTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void enAnimal_InstantiatesCorrectly_true(){
        EndangeredAnimal endangeredAnimal = setUpTheTask();
        assertEquals(true,endangeredAnimal instanceof EndangeredAnimal);
    }

    @Test
    public void enAnimal_InstantiatesWithNameCorrectly_String(){
        EndangeredAnimal endangeredAnimal = setUpTheTask();
        assertEquals("monkey",endangeredAnimal.getName());
    }
    @Test
    public void enAnimal_CorrectlyInstantiatesWithType() throws Exception{
        EndangeredAnimal endangeredAnimal = setUpTheTask();
        assertEquals("endangered",endangeredAnimal.getType());
    }
    @Test
    public void enAnimal_InstantiatesCorrectlyWithId_Int()throws Exception{
        EndangeredAnimal endangeredAnimal = setUpTheTask();
        endangeredAnimal.saveEn();
        assertTrue(endangeredAnimal.getId()>0);
    }


    @Test
    public void saveEn() {
    }

    public EndangeredAnimal setUpTheTask(){
        return new EndangeredAnimal("monkey",EndangeredAnimal.ADULT,EndangeredAnimal.ILL);
    }
}