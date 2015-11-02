/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant;

import java.sql.SQLException;
import static restaurant.SQLFilter.*;

/**
 *
 * @author chatchai
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        dbconnecting();
//        String[] name = selectResName();
//        
//        //print all name
//        for (int i = 0; i < name.length; i++) {
//            System.out.println(name[i]);
//        }
        
        String [] name = selectFilterCuisine("Indian");
        
        //print name of all pizza cuisine
        for (int i = 0; i < name.length; i++) {
            System.out.println("Filter " + name[i]);
        }
        
        destroy();
    }
    
}
