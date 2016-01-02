/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant;

import java.sql.SQLException;



/**
 *
 * @author chatchai
 */
public class Main {

    /**
     * This is where the program start launching
     * @param args
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        DBConnection.dbconnecting();
        FirstFrame form = new FirstFrame();
        form.frame.setVisible(true);
    }
    
}
