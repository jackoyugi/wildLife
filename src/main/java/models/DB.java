package models;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.*;
import java.net.URI;
import java.net.URISyntaxException;

public class DB {
    private static URI dbUri;
    public static Sql2o sql2o;

    static {
        Logger logger = LoggerFactory.getLogger(DB.class);

//        public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife", "jackoyugi", "00100");

        try {
            if (System.getenv("DATABASE_URL") == null) {
                dbUri = new URI("postgres://localhost:5432/wildlife_tracker");
            } else {
                dbUri = new URI(System.getenv("DATABASE_URL"));
            }
            int port = dbUri.getPort();
            String host = dbUri.getHost();
            String path = dbUri.getPath();
            String username = (dbUri.getUserInfo() == null) ? null : dbUri.getUserInfo().split(":")[0];
            String password = (dbUri.getUserInfo() == null) ? null : dbUri.getUserInfo().split(":")[1];
            sql2o = new Sql2o("jdbc:postgresql://" + host + ":" + port + path, "jackoyugi", "00100");
        } catch (URISyntaxException e ) {
            logger.error("Unable to connect to database.");
        }
    }
}



//        public static String connectionString = "jdbc:postgresql://ec2-54-234-28-165.compute-1.amazonaws.com:5432/d4h2r2dnilo18l";
//          static Sql2o sql2o =new Sql2o (connectionString, "mdtyunzqphluue","0c6cc41ae558590758ebc01fd3b3bed6615207519d1cf94e3a1cd27814748406");


