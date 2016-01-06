package SQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static Connection.DBConnection.conn;
/**
 * This class is for getting the reviews from the database.
 * @author Merchad
 */
public class SQLReview extends Connection.DBConnection {
    private static String[] Review;
    /**
    * This method gets the restaurant name and returns the reviews
    * connected to this restaurant.
    */
    public static String[] displayReview(String RestName) {
        
        String sqlDisplayReview = "Select Review from Review where RestName = '"+RestName+"'";
       
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlDisplayReview);
            
             int rowcount = 0;
            
            if (rs.last()) {
            rowcount = rs.getRow();
            rs.beforeFirst();
            }
            
            Review = new String[rowcount];
            int i = 0;
            
	    while(rs.next()){
                Review[i] = rs.getString("Review");
                i++;
            } close(stmt);
            


    } catch (SQLException err) {
        
    }
        return Review;
    }
}
    

