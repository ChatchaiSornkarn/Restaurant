/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant;

import java.sql.*;

/**
 *
 * @author chatchai
 */
public class DBConnection {

    private static final String url = "jdbc:mysql://kenobi.skip.chalmers.se/Team14_db";
    private static final String user = "team14";
    private static final String pass = "112@Chalmers!!!";

    static Connection conn;
    
    public void DBConnection() {
        
    }
    
    /**
     * Creates a connection to YOUR online database
     * @throws SQLException
     */
    public static void dbconnecting() throws SQLException {
        try {
            System.out.println("Connecting to Database...");
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Connecting to Database sucessful");
        }
         catch(Exception e){
            System.out.println("Failed to get connection");
            System.err.println(e.getMessage());
        }
    }
    
    /**
     * Close resultset
     * @param close resultset variable
     */
    public static void close(ResultSet rs){
        
        if(rs !=null){
            try{
               rs.close();
            }
            catch(Exception e){}
        
        }
    }
    
    /**
     * Close Statement
     * @param close Statement variable
     */
    public static void close(java.sql.Statement stmt){
        
        if(stmt !=null){
            try{
               stmt.close();
            }
            catch(Exception e){}
        
        }
    }
     
    /**
     * Disconnect database
     */
    public static void destroy(){
  
        if(conn !=null){
            try{
                conn.close();
            }
            catch(Exception e){}
         
        }
    }
    
}
