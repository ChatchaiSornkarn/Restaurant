package SQL;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import static Connection.DBConnection.conn;

/**
 *
 *This class consists of two parts. The first part has methods that
 * are used to get information from the database into the editing
 * JTextfields and Comboboxes in the editingwindow. The other part 
 * consists of methods that updates the information in the database
 * according to the changes made in the same window.  
 */
public class SQLUpdate extends SQLInsert{
    private static String webSite;
    private static String budgetRange;
    private static String cuisine;
    private static String foodType;
    private static int restId;
    private static int cuisineId;
    private static boolean studentDiscount;
    
  /**
 *This method gets information from the database into the editing
 * JTextfields and Comboboxes in the editingwindow.
 */
    public static String getBudgetRange(String restName) {
         try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select BudgetRange from Restaurant "
                + "INNER JOIN Budget USING (Budget_ID) "
                + "WHERE RestName = \"" + restName + "\"");
                    
                    if (!rs.next()) {
                    return "wrong";
                    }                   
                    else {
                       budgetRange = rs.getString("BudgetRange");
                    }
            
            close(stmt);
        }
        catch(Exception e){
        System.err.println(e.getMessage());
        }

        return budgetRange;
    }
    /**
 *This method gets information from the database into the editing
 * JTextfields and Comboboxes in the editingwindow.
 */
     public static String getCuisine(String restName) {
         try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select Cuisine from Restaurant "
                + "INNER JOIN Restaurant_Cuisine_Types USING (Restaurant_ID) "
                + "INNER JOIN Cuisine_Types USING (Cuisine_ID) "
                + "WHERE RestName = \"" + restName + "\"");
                    
                    if (!rs.next()) {
                    return "wrong";
                    }                   
                    else {
                       cuisine = rs.getString("Cuisine");
                    }
            
            close(stmt);
        }
        catch(Exception e){
        System.err.println(e.getMessage());
        }

        return cuisine;
    }
     /**
 *This method gets information from the database into the editing
 * JTextfields and Comboboxes in the editingwindow.
 */
    public static boolean getStudentDiscount(String restName) {
         try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select StudentDiscount  from Restaurant "
                    + "WHERE RestName = \"" + restName
                    + "\"");
                    
                    if (!rs.next()) {
                    return false;
                    }                   
                    else {
                        if(1 == rs.getInt("StudentDiscount"))
                      studentDiscount = true;
                         else {
                      studentDiscount = false;  
                    }
                    }
            
            close(stmt);
        }
        catch(Exception e){
        System.err.println(e.getMessage());
        }
        
        return studentDiscount;
    }
    /**
 *This method gets information from the database into the editing
 * JTextfields and Comboboxes in the editingwindow.
 */
    public static String getWebadress(String restName){
        
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select website  from Restaurant "
                    + "WHERE RestName = \"" + restName
                    + "\"");
                    
                    if (!rs.next()) {
                    String wrong = "wrong";
                    return wrong;
                    }                   
                    else {
                      webSite = rs.getString("website");  
                    }
            
            close(stmt);
        }
        catch(Exception e){
        System.err.println(e.getMessage());
        }
        
        return webSite;
    }
    /**
 * This method updates the information in the database
 * according to the changes made in the same editingwindows JTextFields and
 * Comboboxes. A=Restaurant name, B= Address c= telephone d= website f= image 
 * g= student discount. Oldname is the input to locate the restaurant that is 
 * going to be edited.
 */
     public static void editRestaurant(String a, String b, String c, String d, String f, int g, String oldName){
        
        try {
            FileInputStream image;
	    PreparedStatement p = conn.prepareStatement("UPDATE Restaurant SET RestName = ?, Address = ?, Telephone = ?, Website = ?, image = ?, StudentDiscount = ? "
                    + "WHERE  RestName = ?");
	    p.setString(1, a);
	    p.setString(2, b);
	    p.setString(3, c); 
            p.setString(4, d);
             try {
                        image = new FileInputStream(new File(f));
                        p.setBlob(5, image);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(SQLInsert.class.getName()).log(Level.SEVERE, null, ex);
                        p.setNull(5,java.sql.Types.BLOB);
                    }
            p.setInt(6, g); 
            p.setString(7, oldName);
            
	    p.executeUpdate();
	    p.close();
	    }
        catch (Exception e) {
			  e.printStackTrace();
		}     
        
    }
     /**
 * This method updates the information in the database
 * according to the changes made in the same editingwindows JTextFields and
 * Comboboxes. A=Restaurant name, B= Address c= telephone d= website f= image 
 * g= student discount. Oldname is the input to locate the restaurant that is 
 * going to be edited.
 */
     public static void editRestaurant(String a, String b, String c, String d, int g, int budget, String oldName  ){
        
        try {
	    PreparedStatement p = conn.prepareStatement("UPDATE Restaurant SET RestName = ?, Address = ?, "
                    + "Telephone = ?, Website = ?, StudentDiscount = ?, Budget_ID = ? WHERE  RestName = ?");
	    p.setString(1, a);
	    p.setString(2, b);
	    p.setString(3, c); 
            p.setString(4, d);
            p.setInt(5, g);
            p.setInt(6, budget);
            p.setString(7, oldName);  
            
	    p.executeUpdate();
	    p.close();
	    }
        catch (Exception e) {
			  e.printStackTrace();
		}     
        
    }
     /**
 * This method updates the information in the database
 * according to the changes made in the same editingwindows JTextFields and
 * Comboboxes. 
 */
     public static void editRestaurant(int cuisineId, int restId){
        
        try {
	    PreparedStatement p = conn.prepareStatement("UPDATE Restaurant_Cuisine_Types "
                + "SET Cuisine_ID = ?  WHERE  Restaurant_ID = ?");
	    p.setInt(1, cuisineId);
            p.setInt(2, restId);
	    p.executeUpdate();
	    p.close();
	    }
        catch (Exception e) {
			  e.printStackTrace();
		}     
        
    }
     /**
 * This method updates the information in the database
 * according to the changes made in the same editingwindows JTextFields and
 * Comboboxes. 
 */
      public static void editRestaurantFoodType(int foodTypeId, int restId){
        
        try {
	    PreparedStatement p = conn.prepareStatement("UPDATE Restaurant_Food_Type "
                + "SET Food_ID = ?  WHERE  Restaurant_ID = ?");
	    p.setInt(1, foodTypeId);
            p.setInt(2, restId);
	    p.executeUpdate();
	    p.close();
	    }
        catch (Exception e) {
			  e.printStackTrace();
		}     
        
    }
 /**
 * This method gets the restaurant id according to the restaurant name provided.
 */
     public static int getRestId(String oldName) {
         try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select Restaurant_ID  from Restaurant "
                    + "WHERE RestName = \"" + oldName
                    + "\"");
                    
                    if (!rs.next()) {
                    return -1;
                    }                   
                    else {
                        restId = rs.getInt("Restaurant_ID");
                    }
            
            close(stmt);
        }
        catch(Exception e){
        System.err.println(e.getMessage());
        }
        
        return restId;
    }
 /**
 * This method gets the cuisine id according to the restaurant name provided.
 */
     public static int getCuisineId(String oldName) {
         try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select Cuisine_ID from Restaurant "
                + "INNER JOIN Restaurant_Cuisine_Types USING (Restaurant_ID) "
                + "WHERE RestName = \"" + oldName + "\"");
                    
                    if (!rs.next()) {
                    return -1;
                    }                   
                    else {
                        cuisineId = rs.getInt("Cuisine_ID");
                    }
            
            close(stmt);
        }
        catch(Exception e){
        System.err.println(e.getMessage());
        }
        
        return cuisineId;
    }
  /**
 * This method gets the food type id according to the restaurant name provided.
 */
      public static String getFoodType(String restName) {
         try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select Food from Restaurant "
                + "INNER JOIN Restaurant_Food_Type USING (Restaurant_ID) "
                + "INNER JOIN Food_Type USING (Food_ID) "
                + "WHERE RestName = \"" + restName + "\"");
                    
                    if (!rs.next()) {
                    return "wrong";
                    }                   
                    else {
                       foodType = rs.getString("Food");
                    }
            
            close(stmt);
        }
        catch(Exception e){
        System.err.println(e.getMessage());
        }

        return foodType;
    }
}