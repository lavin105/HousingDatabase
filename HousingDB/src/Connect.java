import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    public Connection con;
    //method to get the connection string in order to make queries to the housing helper database
    public Connection getConnection() {
        try {
            //connection string
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/HousingHelper?useSSL=false", "lavin105", "Lavin105@m.c.edu");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }


}
