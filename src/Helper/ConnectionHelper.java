package Helper;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
    static final String DB_URL = "jdbc:mysql://localhost/";
    static final String USER = "guest";
    static final String PASS = "guest123";
    public static Connection helper(){
        Connection connection=null;
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    DB_URL, "root", "1234"
            );

        }
        catch(Exception e){
            System.out.println(e);
        }
        return connection;

    }
}
