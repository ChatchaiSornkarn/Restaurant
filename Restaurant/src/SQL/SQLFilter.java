package SQL;

import java.sql.ResultSet;
import java.sql.Statement;
import static Connection.DBConnection.conn;
import java.util.ArrayList;

/**
 * Class: This class is for when you are trying to display for the restaurant thats owner admin own 
 * @author chatchai
 */
public class SQLFilter extends SQLRestaurant {

    private static String[] name;
    private static String[] address;
    private static String[] phone;
    private static String[] website;
    private static ArrayList<String> is;
    
    /**
     * When you are trying to display for the restaurant thats owner admin own 
     * @param user Is which user you are looking for
     * @return rest
     */

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
        
        String[][] rest = new String[name.length][4];

        for (int i = 0; i < name.length; i++) {
            rest[i][0] = name[i];
            rest[i][1] = address[i];
            rest[i][2] = phone[i];
            rest[i][3] = website[i];
        }

        return rest;

    }
    
    public static String[] makeList(String a){
		is = new ArrayList<String>();
		String[] array = null;
		
        try {
	        Statement stmt = conn.createStatement();
                stmt.setQueryTimeout(30);
                ResultSet r = stmt.executeQuery(a);
            while(r.next() ){
    	        is.add(r.getString(1));
	        }
            array = is.toArray(new String[is.size()]);
            close(stmt);
	     }catch (Exception e) {
		  e.printStackTrace();
	}     
	      return array;    
    }
}
