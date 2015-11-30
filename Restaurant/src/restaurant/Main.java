/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant;

import java.sql.SQLException;

import javax.swing.JFrame;

import static restaurant.SQLFilter.SelectFilterBudget;


/**
 *
 * @author chatchai
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        DBConnection.dbconnecting();
        FirstFrame form = new FirstFrame();
        form.frame.setVisible(true);
        
    }
    
}
