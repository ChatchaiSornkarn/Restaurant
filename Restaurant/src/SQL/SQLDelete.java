package SQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import static DBConnection.DBConnection.conn;

/**
 * Class: Delete restaurant
 * @author chatchai
 */
public class SQLDelete extends SQLInsert{
    
     public static void deleteRestaurant(String a, String b, String c){
        
        try {
            
	    PreparedStatement p = conn.prepareStatement("DELETE FROM Restaurant WHERE RestName = ? and Address = ? and Telephone = ?");
	    p.setString(1, a);
	    p.setString(2, b);
	    p.setString(3, c);  
            
	    p.executeUpdate();
	    p.close();
	    }
        catch (Exception e) {
			  e.printStackTrace();
		}     
        
    }
}
