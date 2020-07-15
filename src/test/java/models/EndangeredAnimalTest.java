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
    public void enAnimalSavesCorrectly_ToTheDatabase() throws Exception{
        EndangeredAnimal endangeredAnimal = setUpTheTask();
        endangeredAnimal.saveEn();
        assertTrue(EndangeredAnimal.allEnda().get(0).equals(endangeredAnimal));
    }
    @Test
    public void EnAnimal_FindsEndangeredAnimalWithTheSameId() throws Exception{
        EndangeredAnimal firstendangered = setUpTheTask();
        firstendangered.saveEn();
        EndangeredAnimal secondendangered = new EndangeredAnimal("owl",EndangeredAnimal.ADULT,EndangeredAnimal.ILL);
        secondendangered.saveEn();
        assertEquals(EndangeredAnimal.findEndangered(secondendangered.getId()),secondendangered);
    }
    @Test
    public void enAnimalsReturnsTrueIfAnimalsAreTheSame() throws Exception{
        EndangeredAnimal firstendangered = setUpTheTask();
        firstendangered.saveEn();
        EndangeredAnimal secondendangered = setUpTheTask();
        secondendangered.saveEn();
        assertTrue(firstendangered.equals(secondendangered));
    }
    @Test
    public void enAnimalSave_returnsTrueIfNamesAreTheSame() throws Exception{
        EndangeredAnimal endangeredAnimal= setUpTheTask();
        endangeredAnimal.saveEn();
        assertEquals(EndangeredAnimal.allEnda().get(0),endangeredAnimal);
    }
    @Test
    public void enAnimalReturnsAllInstancesOfEndangeredAnimals_true() throws Exception{
        EndangeredAnimal firstendangered = setUpTheTask();
        firstendangered.saveEn();
        EndangeredAnimal secondendangered = setUpTheTask();
        secondendangered.saveEn();
        assertEquals(EndangeredAnimal.allEnda().get(0),firstendangered);
        assertEquals(EndangeredAnimal.allEnda().get(1),secondendangered);
    }
    @Test
    public void update_EndangeredAnimal_true() throws Exception{
        EndangeredAnimal firstendangered = setUpTheTask();
        firstendangered.saveEn();
        firstendangered.update("cruel");
    }
    @Test
    public void delete_EndangeredAnimalById()throws Exception{
        EndangeredAnimal firstendangered = setUpTheTask();
        firstendangered.saveEn();
        int deleteEndangeredAnimalById = firstendangered.getId();
        firstendangered.delete();
        assertEquals(null,EndangeredAnimal.findEndangered(deleteEndangeredAnimalById));

    }


    public EndangeredAnimal setUpTheTask(){
        return new EndangeredAnimal("monkey",EndangeredAnimal.ADULT,EndangeredAnimal.ILL);
    }
}