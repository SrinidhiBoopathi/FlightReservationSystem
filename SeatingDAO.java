package dao;

import Helper.ConnectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class SeatingDAO {

    public Connection getSeatingConnection(){
        ConnectionHelper help = new ConnectionHelper();
        Connection connect = help.helper();

        return connect;

    }

    public ResultSet querySeatDetails(String field, String condition){

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            String use = "use flight_reservation";
            statement = getSeatingConnection().createStatement();
            statement.execute(use);
            String query="select "+field+" from SeatDetails where Flight_Number='"+condition+"'";
            resultSet = statement.executeQuery(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }
}
