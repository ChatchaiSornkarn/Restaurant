
package ResultPanel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import static DBConnection.DBConnection.conn;

/**
 * This class is for inserting reviews to the database.
 */
public class Review {
     /**
 * This method takes the restaurant name, user name and review
 * and adds it to the database connected to the right restaurant.
 */
    public static void insertReview(String RestName,String username, String review) {
        
        
        	try {
		PreparedStatement p;
                Statement stmt = conn.createStatement();
                ResultSet r = stmt.executeQuery("SELECT * from Review ");
		      

	    	  
	    p = conn.prepareStatement("INSERT INTO Review VALUES (? ,?, ?)");
	    p.setString(1, RestName);
            p.setString(2, username);
	    p.setString(3, review);
	      
	    p.executeUpdate();
	    p.close();
	    }catch (Exception e) {;}
			  
		}     
        }



