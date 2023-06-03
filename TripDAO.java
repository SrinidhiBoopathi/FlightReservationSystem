package dao;

import Helper.ConnectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Set;

public class TripDAO {
    public static Connection getTripConnection(){
        ConnectionHelper help = new ConnectionHelper();
        Connection connect = help.helper();

        return connect;

    }

    public static ResultSet queryTripDetails(String query, Map<String,String> conditionMap){
       // String query="select "+queryField+" from TripDetails where ";
        int i=0;
        Set<String> conditionKeys = conditionMap.keySet();

//        while(i<conditionKeys.size()-1){
//
//        }
//
//        while(i<conditionMap.size()-1){
//            conditionMap.keySet()
//        }
        //query = query+entry.getKey()+"='"+entry.getValue()+"'";

//        for (Map.Entry<String,String> entry : conditionMap.entrySet()){
//            query = query+entry.getKey()+"='"+entry.getValue()+"'";
//
//        }
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            String use = "use flight_reservation";
            Connection connection = getTripConnection();
            statement = connection.createStatement();
            statement.execute(use);
            resultSet = statement.executeQuery(query);
            System.out.println("resultset:"+resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }


    public static int countQuery(String query){
        Statement statement = null;
        int count = 0;
        try {
            String use = "use flight_reservation";
            Connection connection = getTripConnection();
            statement = connection.createStatement();
            statement.execute(use);
            count = statement.executeUpdate(query);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;

    }

}
