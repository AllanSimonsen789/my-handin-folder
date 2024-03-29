package utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public class SettingsTest {
    
    
    public void testGetSingleProperty(){
        assertEquals("3307", Settings.getPropertyValue("db.port"));
    }
    
    
    public void testGetDEV_DBConnection(){
        //This test is a little bit hacky since it uses a property that can be changed by users
        //Is Better than NO test
        String dbName = Settings.getPropertyValue("db.database");
        assertEquals(String.format("jdbc:mysql://localhost:3307/%s",dbName), Settings.getDEV_DBConnection());
    }
    
   
    public void testGetTEST_DBConnection(){
        //This test is also a little bit hacky since it uses a property that can be changed by users
        //Is Better than NO test
        String dbName = Settings.getPropertyValue("dbtest.database");
        assertEquals(String.format("jdbc:mysql://localhost:3307/%s",dbName), Settings.getTEST_DBConnection());
    }
    
}
