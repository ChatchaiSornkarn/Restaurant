
package restaurant;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static restaurant.DBConnection.conn;


public class Review {
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



