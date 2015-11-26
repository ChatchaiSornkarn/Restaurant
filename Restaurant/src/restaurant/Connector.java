package restaurant;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;


public class Connector extends DBConnection{
    String url = "jdbc:mysql://kenobi.skip.chalmers.se/Team14_db";
    String user = "team14";
    String pass = "112@Chalmers!!!";
    private int ex;
   static private int index;
	
	// A method to read data from database and put that data in an array
	public String[] makeList(String a){
	   ArrayList<String> is =	new ArrayList<String>();
           String[] array = null;
		
	   try {
	   
	       Statement statement = conn.createStatement();
              statement.setQueryTimeout(30);
            ResultSet r = statement.executeQuery(a);
            while(r.next() ){
    	        is.add(r.getString(1));
	        } 
            close(statement);
            array = is.toArray(new String[is.size()]);
	     }catch (SQLException e) {
		 // TODO Auto-generated catch block
		  e.printStackTrace();
	}     
	      return array;    
    }
	
	// Creating a review method
	public void makeRevie(String table,String a, String b){
	try {
		//Connection connection = null;
		PreparedStatement p;
      //  connection  = DriverManager.getConnection(url,user,pass);
        Statement statement = conn.createStatement();
        statement.setQueryTimeout(30);
		int index = 0;
	    ResultSet r = statement.executeQuery("select * from revie ");
		      
	       while(r.next())
		      {index++;}
	    	  
	    p = conn.prepareStatement("INSERT INTO "+table +" VALUES ( ?, ?, ?)");
	     p.setInt(1, index);
	    p.setString(2, a);
	    p.setString(3, b);
	      
	    p.executeUpdate();
	    p.close();
            statement.close();

	    }catch (SQLException e) {
			  e.printStackTrace();
		}     
    	
  }
	
	public ImageIcon[] makeIcon(String a){
		ArrayList<ImageIcon> is =	new ArrayList<ImageIcon>();
		ImageIcon[] im = null;
		try {
			
	     
	        Statement statement = conn.createStatement();
            statement.setQueryTimeout(60);
            
            ResultSet r = statement.executeQuery(a);
            while(r.next() ){
            
            	if(r.getBytes(1) != null ){
                  byte[] imageBytes = r.getBytes(1);
    	         
    	          ImageIcon  icon = new ImageIcon(imageBytes);
         		  Image image = icon.getImage();
         		  Image bild= image.getScaledInstance(180, 100,  java.awt.Image.SCALE_SMOOTH);
         		  icon = new ImageIcon(bild);
         		  is.add(icon);
         		  
            	} else if (r.getBytes(1)== null) {
            		ImageIcon  icon = new ImageIcon("mapp/Interior-Decorative-Design-New-York-Terrace-Restaurant-HD-wallpaper.jpg");
            		Image image = icon.getImage();
             		Image bild= image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
             		icon = new ImageIcon(bild);
             		is.add(icon);
            	  
            	 }
           
	        }  statement.close();
	        im = is.toArray(new ImageIcon[is.size()]);
            
	     }catch (SQLException e) {
	}     
	      
		return im; 
	}
	
	//login
	boolean login(String[] queryusername,String[] querypassword, String username, char[] password){
		String b = new String(password);
		boolean f = false;
	    for(int i = 0; i<queryusername.length;i++){
	      if(queryusername[i].equals(username) && querypassword[i].equals(b)){
			 f= true;
		  }
	}
			return f;
	}	
        
		public void makeRestaurant(String a, String b, String c,String d,
	        String e,   String f ,  String g, List h){
		int index = 0;
		int ex = 0;
try {
	
	InputStream image;	
	//Connection connection = null;
	PreparedStatement p;
    //connection  = DriverManager.getConnection(url,user,pass);
    Statement statement = conn.createStatement();
    statement.setQueryTimeout(30);
        
    ResultSet r = statement.executeQuery("select * from Restaurant");
	      
       while(r.next())
	      {index++;}
    	  index++;
    p = conn.prepareStatement("INSERT INTO Restaurant VALUES ( ?, ?, ?, ?, ?, ?, ?,?)");
     p.setInt(1, index);
    p.setString(2, a);
    p.setString(3, b);
    p.setString(4, c);
    p.setString(5, d);
           try {
                image = new FileInputStream(new File(e));
                p.setBlob(6, image);
            } catch (FileNotFoundException tr) {
                Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
                p.setNull(6,java.sql.Types.BLOB);
            }
             int bu = 0;
        
    ResultSet rs = statement.executeQuery("select * from Budget where BudgetRange = \"" + f + "\"");
	      
       while(rs.next())
	  { bu = rs.getInt("Budget_ID");}
               p.setInt(7, bu);
               int di = Integer.parseInt(g);
               p.setInt(8, di);
for(int i = 0;h.size()>i;i++){
        ResultSet ris = statement.executeQuery("select Cuisine_ID from Cuisine_Types where Cuisine = \"" + h.get(i) + "\"");
        while(ris.next()){
             ex = ris.getInt(1);
                 }
	statement.executeUpdate("INSERT INTO Restaurant_Cuisine_Types VALUES(\""+ index  + "\",\"" + ex + "\")");
        }
  index = 0;
    p.executeUpdate();
    p.close();
    }catch (SQLException di) {
		  di.printStackTrace();
	}    
        
	}

         String[] cuisine(String cuisine){
            
            String[] array;
            String b = "";
            String a =  "";
            if(cuisine.contains("All")){
                a= (" ");
                b= "";
                array = new String[] {a,b};
               
            }else{
               b = "and Restaurant.Restaurant_ID = Restaurant_Cuisine_Types.Restaurant_ID " +
                 "and Restaurant_Cuisine_Types.Cuisine_ID = Cuisine_Types.Cuisine_ID "
                 + "and Cuisine_Types.Cuisine = \""  +  cuisine  + "\"";
               array = new String[] {",Restaurant_Cuisine_Types,Cuisine_Types ",b};
            }
                
          return array;
        }
        String[] food(String food){
            
            String[] array;
            String b = "";
            String a =  "";
            if(food.contains("All")){
                a= (" ");
                b= "";
                array = new String[] {a,b};
               
            }else{
               b = "and Restaurant.Restaurant_ID = Restaurant_Food_Type.Restaurant_ID " +
                 "and Restaurant_Food_Type.Food_ID = Food_Type.Food_ID "
                 + "and Food_Type.Food = \""  +  food  + "\"";
               array = new String[] {",Restaurant_Food_Type,Food_Type ",b};
            }
                
          return array;
        } 
         
               
        String sd(int a){
                   
           String discount = "";    
            if(a == 1){
                discount = " and StudentDiscount = \"1\"";
            }return discount;
        }
               
                String[] restFilter(String[] cuisine, String b, String sd, String[] food){
                    String[] a = makeList("SELECT Restaurant.RestName FROM Restaurant, Budget"
                 +  cuisine[0] + food[0] +
                   "where Restaurant.Budget_ID = Budget.Budget_ID and Budget.BudgetRange = \"" + 
                  b  + "\"" + cuisine[1] +sd + food[1]);
        return a;
        }    
                
           String[] addFilter(String[] cuisine, String b,String sd, String[] food){
                    String[] a = makeList("SELECT Address FROM Restaurant, Budget"
                  + cuisine[0] + food[0] +
                   "where Restaurant.Budget_ID = Budget.Budget_ID and Budget.BudgetRange = \"" + 
                  b  + "\"" + cuisine[1] +sd + food[1]);
        return a;
        }         
           
            String[] telFilter(String[] cuisine, String b, String sd, String[] food ){
                    String[] a = makeList("SELECT Telephone FROM Restaurant, Budget"
                  + cuisine[0] +food[0] +
                   "where Restaurant.Budget_ID = Budget.Budget_ID and Budget.BudgetRange = \"" + 
                  b  + "\"" + cuisine[1] +sd + food[1]);
        return a;
        }         
             ImageIcon[] imFilter(String[] cuisine, String b, String sd, String[] food){
                    ImageIcon[] a = makeIcon("SELECT image FROM Restaurant, Budget"
                  + cuisine[0] +food[0] +
                   "where Restaurant.Budget_ID = Budget.Budget_ID and Budget.BudgetRange = \"" + 
                  b  + "\"" + cuisine[1] +sd + food[1]);
        return a;
        }         
  
       //         + "WHERE StudentDiscount = \"1\" ORDER BY RestName");
        
    
}
    
    

