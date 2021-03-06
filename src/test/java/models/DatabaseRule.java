package models;

import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DatabaseRule extends ExternalResource {
    @Override
    protected void before() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "jackoyugi", "00100");
    }

//        String connectionString = "jdbc:postgresql://ec2-54-234-28-165.compute-1.amazonaws.com:5432/d4h2r2dnilo18l";
//        DB.sql2o = new Sql2o(connectionString, "mdtyunzqphluue", "0c6cc41ae558590758ebc01fd3b3bed6615207519d1cf94e3a1cd27814748406");
//    }

    @Override
    protected void after() {
        try(Connection con = DB.sql2o.open()) {
            String deleteAnimalsQuery = "DELETE FROM animals *;";
            String deleteEndangeredAnimalQuery = "DELETE FROM Animals *;";
            String deleteSightingsQuery = "DELETE FROM sightings *;";
            String deleteRangersQuery = "DELETE FROM rangers *;";
            String deleteLocationsQuery = "DELETE FROM locations *;";
            con.createQuery(deleteAnimalsQuery).executeUpdate();
            con.createQuery(deleteEndangeredAnimalQuery).executeUpdate();
            con.createQuery(deleteSightingsQuery).executeUpdate();
            con.createQuery(deleteRangersQuery).executeUpdate();
            con.createQuery(deleteLocationsQuery).executeUpdate();

        }
    }
}
