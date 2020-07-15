package models;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void Location_instantiatesCorrectly_true(){
        Location location = new Location("jackwonder");
        assertEquals(true, location instanceof Location);
    }
    @Test
    public void Location_instantiatesWithName_String() {
        Location location = new Location("jackwonder");
        assertEquals("jackwonder", location.getName());
    }
    @Test
    public void Location_instantiatesWithAnId() {
        Location location = new Location("jackwonder");
        location.save();
        assertTrue(location.getId()>0);
    }
    @Test
    public void save_savesCorrectly() {
        Location location = new Location("jackwonder");
        location.save();
        assertTrue(Location.all().get(0).equals(location));
    }
    @Test
    public void find_returnsLocationWIthSameID_secondLocation(){
        Location location = new Location("jack");
        location.save();
        Location locationTwo = new Location("wonder");
        locationTwo.save();
        assertEquals(Location.find(locationTwo.getId()), locationTwo);
    }
    @Test
    public void equals_returnsTrueIfLocationsAreSame(){
        Location location = new Location("jackwonder");
        Location locationTwo = new Location("jackwonder");
        assertTrue(location.equals(locationTwo));
    }
    @Test
    public void save_returnsTrueIfNamesAreTheSame(){
        Location location = new Location("jackwonder");
        location.save();
        assertEquals(Location.all().get(0), location);
    }
    @Test
    public void all_returnsAllInstancesOfLocations_true(){
        Location locationOne = new Location("jack");
        locationOne.save();
        Location locationTwo = new Location("wonder");
        locationTwo.save();
        assertEquals(Location.all().get(0), locationOne);
        assertEquals(Location.all().get(1), locationTwo);
    }
    @Test
    public void save_assignsIdToObject() {
        Location location = new Location("jackwonder");
        location.save();
        Location savedLocation = Location.all().get(0);
        assertEquals(location.getId(), savedLocation.getId());
    }
    @Test
    public void update_updateLocation_true(){
        Location location = new Location("jack");
        location.save();
        location.update("jack");
    }
    @Test
    public void delete_deletesLocation_true(){
        Location location = new Location("jackwonder");
        location.save();
        int locationId = location.getId();
        location.delete();
        assertEquals(null, Location.find(locationId));
    }




}