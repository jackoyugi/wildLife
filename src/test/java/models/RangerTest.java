package models;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class RangerTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void Ranger_instantiatesCorrectly_true(){
        Ranger ranger = new Ranger("jackwonder", 0001);
        assertEquals(true, ranger instanceof Ranger);
    }
    @Test
    public void Ranger_instantiatesWithName_String() {
        Ranger ranger = new Ranger("jackwonder", 0001);
        assertEquals("jackwonder", ranger.getName());
    }
    @Test
    public void Ranger_instantiatesWithAnId() {
        Ranger ranger = new Ranger("jackwonder", 0001);
        ranger.save();
        assertTrue(ranger.getId()>0);
    }
    @Test
    public void save_savesCorrectly() {
        Ranger ranger = new Ranger("jackwonder", 0001);
        ranger.save();
        assertTrue(Ranger.all().get(0).equals(ranger));
    }
    @Test
    public void find_returnsRangerWIthSameID_secondRanger(){
        Ranger ranger = new Ranger("jack", 0001);
        ranger.save();
        Ranger rangerTwo = new Ranger("wonder", 0001);
        rangerTwo.save();
        assertEquals(Ranger.find(rangerTwo.getId()), rangerTwo);
    }
    @Test
    public void equals_returnsTrueIfRangersAreSame(){
        Ranger ranger = new Ranger("jackwonder", 0001);
        Ranger rangerTwo = new Ranger("jackwonder", 0001);
        assertTrue(ranger.equals(rangerTwo));
    }
    @Test
    public void save_returnsTrueIfNamesAreTheSame(){
        Ranger ranger = new Ranger("jackwonder", 0001);
        ranger.save();
        assertEquals(Ranger.all().get(0), ranger);
    }
    @Test
    public void all_returnsAllInstancesOfRangers_true(){
        Ranger rangerOne = new Ranger("jack", 0001);
        rangerOne.save();
        Ranger rangerTwo = new Ranger("wonder", 0001);
        rangerTwo.save();
        assertEquals(Ranger.all().get(0), rangerOne);
        assertEquals(Ranger.all().get(1), rangerTwo);
    }
    @Test
    public void save_assignsIdToObject() {
        Ranger ranger = new Ranger("jackwonder", 0001);
        ranger.save();
        Ranger savedRanger = Ranger.all().get(0);
        assertEquals(ranger.getId(), savedRanger.getId());
    }
    @Test
    public void update_updateRanger_true(){
        Ranger ranger = new Ranger("jackwonder", 0001);
        ranger.save();
        ranger.update("Simon", 90899);
    }
    @Test
    public void delete_deletesRanger_true(){
        Ranger ranger = new Ranger("jackwonder", 0001);
        ranger.save();
        int rangerId = ranger.getId();
        ranger.delete();
        assertEquals(null, Ranger.find(rangerId));
    }


}