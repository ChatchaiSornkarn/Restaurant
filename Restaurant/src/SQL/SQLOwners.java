package SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static Connection.DBConnection.conn;

/**
 * This class is to get all user names from the database.
 * @author Merchad
 */
public class SQLOwners extends Connection.DBConnection{

    private static String[] userOwners;

    public static String[] displayUsers() {

        String sqlDisplayReview = "Select User from Login";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlDisplayReview);

            int rowcount = 0;

            if (rs.last()) {
                rowcount = rs.getRow();
                rs.beforeFirst();
            }

            userOwners = new String[rowcount];
            int i = 0;

            while (rs.next()) {
                userOwners[i] = rs.getString("User");
                i++;
            }
            close(stmt);

        } catch (SQLException err) {

        }
        return userOwners;
    }
}
