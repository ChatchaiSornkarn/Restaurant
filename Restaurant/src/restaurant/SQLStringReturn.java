/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author team 14
 */
public class SQLStringReturn {
    
     
    
    public String[] makeList(String a){
	ArrayList<String> is = new ArrayList<String>();
		String[] array = null;
		
        try {
	        Statement stmt = conn.createStatement();
                stmt.setQueryTimeout(30);
                ResultSet r = stmt.executeQuery(a);
            while(r.next() ){
    	        is.add(r.getString(1));
	        }  conn.close();
            array = is.toArray(new String[is.size()]);
	     }catch (Exception e) {
		  e.printStackTrace();
	}     
	      return array;    
    }
	
	// Creating a review method
	public void makeReview(String a, String b){
	try {
		PreparedStatement p;
                Statement stmt = conn.createStatement();
                stmt.setQueryTimeout(30);
		int index = 0;
                ResultSet r = stmt.executeQuery("select * from revie ");
		      
	       while(r.next())
		      {index++;}
	    	  
	    p = conn.prepareStatement("INSERT INTO revie VALUES (?, ?, ?)");
	    p.setInt(1, index);
	    p.setString(2, a);
	    p.setString(3, b);
	      
	    p.executeUpdate();
	    p.close();
	    conn.close();
	    }catch (Exception e) {
			  e.printStackTrace();
		}     
        }
}
