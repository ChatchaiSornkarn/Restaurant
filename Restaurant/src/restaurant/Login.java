/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static restaurant.DBConnection.conn;

/**
 *
 * @author chatchai
 */
public class Login {
   
    boolean adminLogin = false; 
   

    
    public void AdminLogin(String username, String password, JFrame frame){ 
        
        
                        String sqlLoginVerify = "Select User, Password from Login where User = '"+username+"' and Password = '"+password+"'";
                        try {
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(sqlLoginVerify);
                        
                        
                        
                                if (rs.next()) {
                                 JOptionPane.showMessageDialog(null, "Login Successful!"); 
                                 this.adminLogin = true;
                                 
                                } else if (username.equals("") && (password.equals(""))) {
                                  JOptionPane.showMessageDialog(null, "Please enter your username and password.");
                                  
                                  
                                } else if (username.equals("")) {
                                  JOptionPane.showMessageDialog(null, "Username field is empty. Please enter your username.");
                                  
                                } else if (password.equals("")) {
                                  JOptionPane.showMessageDialog(null, "Password field is empty. Please enter your password.");
                                
                                  
                                } else {
                                    JOptionPane.showMessageDialog(null, "Login Failed. Please try again.");
                                }
                            
                                } catch(SQLException err) {;}
    }
}
