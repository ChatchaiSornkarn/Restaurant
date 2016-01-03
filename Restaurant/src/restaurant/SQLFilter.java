package restaurant;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.Statement;
import static restaurant.DBConnection.conn;
import static restaurant.SQLStringReturn.makeList;

/**
 * Class: This class is for when you are trying to display for the restaurant thats owner admin own 
 * @author chatchai
 */
public class SQLFilter extends SQLRestaurant {

    private static String[] name;
    private static String[] address;
    private static String[] phone;
    private static String[] website;
    
    /**
     * When you are trying to display for the restaurant thats owner admin own 
     * @param user Is which user you are looking for
     * @return rest
     */

    public static String[][] SelectOwnerRestaurants(String user) {
        name = makeList("Select RestName from Restaurant "
                + "INNER JOIN Login USING (Login_ID) "
                + "WHERE User = \""+user
                + "\" ORDER BY RestName");
                
        address = makeList("Select Address from Restaurant "
                + "INNER JOIN Login USING (Login_ID) "
                + "WHERE User = \""+user
                + "\" ORDER BY RestName");

        phone = makeList("Select Telephone from Restaurant "
                + "INNER JOIN Login USING (Login_ID) "
                + "WHERE User = \""+user
                + "\" ORDER BY RestName");
        
        website = makeList("Select Website from Restaurant "
                + "INNER JOIN Login USING (Login_ID) "
                + "WHERE User = \""+user
                + "\" ORDER BY RestName");
        
        String[][] rest = new String[name.length][4];

        for (int i = 0; i < name.length; i++) {
            rest[i][0] = name[i];
            rest[i][1] = address[i];
            rest[i][2] = phone[i];
            rest[i][3] = website[i];
        }

        return rest;

    }
}
