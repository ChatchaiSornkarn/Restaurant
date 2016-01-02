/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.Statement;
import static restaurant.DBConnection.conn;

/**
 *
 * @author chatchai
 */
public class SQLFilter extends SQLRestaurant {

    private static String[] name;
    private static String[] address;
    private static String[] phone;
    private static String[] website;
    /**
     * Select all Restaurant name from specific cuisine
     *
     * @param cuisine type of cuisine
     * @return cuisineRest[][] [differents restaurant], [0]name [1]address [2]phoneNumber
     */
    public static String[][] selectFilterCuisine(String cuisine) {
        name = makeList("Select RestName from Restaurant "
                + "INNER JOIN Restaurant_Cuisine_Types USING (Restaurant_ID) "
                + "INNER JOIN Cuisine_Types USING (Cuisine_ID)"
                + "WHERE Cuisine = \"" + cuisine
                + "\" ORDER BY RestName");

        address = makeList("Select Address from Restaurant "
                + "INNER JOIN Restaurant_Cuisine_Types USING (Restaurant_ID) "
                + "INNER JOIN Cuisine_Types USING (Cuisine_ID)"
                + "WHERE Cuisine = \"" + cuisine
                + "\" ORDER BY RestName");

        phone = makeList("Select Telephone from Restaurant "
                + "INNER JOIN Restaurant_Cuisine_Types USING (Restaurant_ID) "
                + "INNER JOIN Cuisine_Types USING (Cuisine_ID)"
                + "WHERE Cuisine = \"" + cuisine
                + "\" ORDER BY RestName");

        String[][] cuisineRest = new String[name.length][3];

        for (int i = 0; i < name.length; i++) {
            cuisineRest[i][0] = name[i];
            cuisineRest[i][1] = address[i];
            cuisineRest[i][2] = phone[i];
        }

        return cuisineRest;
    }

    /**
     * Select all restaurant that have this range of budget
     *
     * @param BudgetRange range of budget
     * @return bugetRest[][] [differents restaurant], [0]name [1]address [2]phoneNumber
     */
    public static String[][] SelectFilterBudget(String BudgetRange) {
        name = makeList("Select RestName from Restaurant "
                + "INNER JOIN Budget USING (Budget_ID) "
                + "WHERE BudgetRange = \"" + BudgetRange + "\" ORDER BY RestName");

        address = makeList("Select Address from Restaurant "
                + "INNER JOIN Budget USING (Budget_ID) "
                + "WHERE BudgetRange = \"" + BudgetRange + "\" ORDER BY RestName");

        phone = makeList("Select Telephone from Restaurant "
                + "INNER JOIN Budget USING (Budget_ID) "
                + "WHERE BudgetRange = \"" + BudgetRange + "\" ORDER BY RestName");

        website = makeList("Select Website from Restaurant "
                + "INNER JOIN Budget USING (Budget_ID) "
                + "WHERE BudgetRange = \"" + BudgetRange + "\" ORDER BY RestName");
        
        String[][] bugetRest = new String[name.length][4];

        for (int i = 0; i < name.length; i++) {
            bugetRest[i][0] = name[i];
            bugetRest[i][1] = address[i];
            bugetRest[i][2] = phone[i];
            bugetRest[i][3] = website[i];
        }

        return bugetRest;

    }

    public static String[][] SelectFilterDiscount() {
        name = makeList("Select RestName from Restaurant "
                + "WHERE StudentDiscount = \"1\" ORDER BY RestName");

        address = makeList("Select Address from Restaurant "
                + "WHERE StudentDiscount = \"1\" ORDER BY RestName");

        phone = makeList("Select Telephone from Restaurant "
                + "WHERE StudentDiscount = \"1\" ORDER BY RestName");

        String[][] discountRest = new String[name.length][3];

        for (int i = 0; i < name.length; i++) {
            discountRest[i][0] = name[i];
            discountRest[i][1] = address[i];
            discountRest[i][2] = phone[i];
        }

        return discountRest;
    }

    public static String[][] SelectOwnerRestaurants(String user) {
        name = makeList("Select RestName from Restaurant "
                + "INNER JOIN Login USING (Login_ID) "
                + "WHERE User = \""+user
                + "\" ORDER BY RestName");
                
        address = makeList("Select Address from Restaurant "
                + "INNER JOIN Login USING (Login_ID) "
                + "WHERE User = \""+user
                + "\" ORDER BY RestName");

        phone = makeList("Select Telephone from Restaurant "
                + "INNER JOIN Login USING (Login_ID) "
                + "WHERE User = \""+user
                + "\" ORDER BY RestName");
        
        website = makeList("Select Website from Restaurant "
                + "INNER JOIN Login USING (Login_ID) "
                + "WHERE User = \""+user
                + "\" ORDER BY RestName");
        
        String[][] discountRest = new String[name.length][4];

        for (int i = 0; i < name.length; i++) {
            discountRest[i][0] = name[i];
            discountRest[i][1] = address[i];
            discountRest[i][2] = phone[i];
            discountRest[i][3] = website[i];
        }

        return discountRest;

    }
}
