package restaurant;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static restaurant.DBConnection.conn;

public class SQLReview extends SQLStringReturn {
    private static String[] Review;
    
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
    

