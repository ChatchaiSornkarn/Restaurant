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
    boolean ownAdmin = false;

    public void AdminLogin(String username, String password, JFrame frame) {

        String sqlLoginVerify = "Select User, Password from Login where User = '" + username + "' and Password = '" + password + "' and User = 'bob' and Password = 'me'";
        try {
            Statement stmt = conn.createStatement();
            Statement stmt2 = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlLoginVerify);
            ResultSet r = stmt2.executeQuery("Select User, Password from Login where User = '"+username+"' and Password = '"+password+"'");
                        

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Super Login Successful!");
                this.adminLogin = true;
            } 
            else if (r.next()){
                JOptionPane.showMessageDialog(null, "Own Login Successful!");
                this.ownAdmin = true;
            }
            else if (username.equals("") && (password.equals(""))) {
                JOptionPane.showMessageDialog(null, "Please enter your username and password.");

            } else if (username.equals("")) {
                JOptionPane.showMessageDialog(null, "Username field is empty. Please enter your username.");

            } else if (password.equals("")) {
                JOptionPane.showMessageDialog(null, "Password field is empty. Please enter your password.");

            } else {
                JOptionPane.showMessageDialog(null, "Login Failed. Please try again.");
            }

        } catch (SQLException err) {;
        }
    }
}
