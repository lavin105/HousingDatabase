import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    public Connection con;
    public Connection getConnection() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Housing?useSSL=false", "lavin105", "Lavin105@m.c.edu");
        } catch (SQLException e) {

        }
        return con;
    }

}
