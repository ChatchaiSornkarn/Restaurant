package SQL;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import DBConnection.Connector;
import static DBConnection.DBConnection.conn;
import static Frame.FirstFrame.username;

/**
 * Class: This class is for adding the restaurant
 *
 * @author
 */
public class SQLInsert extends Connector {

    /**
     * This class is for adding the restaurant
     */
    public static void addRestaurant(String a, String b, String c, String d, String f, String g, boolean h, String i) {

        try {
            Statement stm = conn.createStatement();
            stm.setQueryTimeout(30);
            // get the id of owner who want to insert restaurant
            ResultSet r = stm.executeQuery("select Login_ID from Login where User = \"" + username + "\"");
            int userID = 0;
            
            // get the id of owner who want to insert restaurant
            while (r.next()) {
                userID = r.getInt(1);
            }
            
            // insert restaurant
            FileInputStream image;
            PreparedStatement p = conn.prepareStatement("INSERT INTO Restaurant (RestName,Address,Telephone,Website,image,Budget_ID,StudentDiscount,Login_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            p.setString(1, a);
            p.setString(2, b);
            p.setString(3, c);
            p.setString(4, d);

            try {
                image = new FileInputStream(new File(f));
                p.setBlob(5, image);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SQLInsert.class.getName()).log(Level.SEVERE, null, ex);
                p.setNull(5, java.sql.Types.BLOB);
            }
            int budgetID = 0;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select Budget_ID from Budget where BudgetRange = \"" + g + "\"");
            while (rs.next()) {
                budgetID = rs.getInt("Budget_ID");
            }
            p.setInt(6, budgetID);

            if (h == true) {
                p.setInt(7, 1);
            } else {
                p.setInt(7, 0);
            }

            p.setInt(8, userID);

            p.executeUpdate();
            p.close();

            //get the id of cuisine type
            Statement stm2 = conn.createStatement();
            stm2.setQueryTimeout(30);
            ResultSet r2 = stm2.executeQuery("select Cuisine_ID from Cuisine_Types where Cuisine = \"" + i + "\"");
            int cuisineID = 0;

            while (r2.next()) {
                cuisineID = r2.getInt(1);
            }

            //get the restaurant id that just been insert
            Statement stm3 = conn.createStatement();
            stm3.setQueryTimeout(30);
            ResultSet r3 = stm3.executeQuery("select Restaurant_ID from Restaurant where RestName = \"" + a + "\"");
            int restID = 0;

            while (r3.next()) {
                restID = r3.getInt(1);
            }
            
            //insert the cuisine type of restaurant
            PreparedStatement p2 = conn.prepareStatement("INSERT INTO Restaurant_Cuisine_Types VALUES (" + restID + "," + cuisineID + ")");
            p2.executeUpdate();
            p2.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
