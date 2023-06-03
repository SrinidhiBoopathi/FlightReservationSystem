package dao;

import Helper.ConnectionHelper;

import java.sql.*;

public class FlightDAO {
    public static void insertFlight(String airline,String flight_num,String flight_name,int capacity,int es,int bs,int cost_es,int cost_bs,String board_time,String drop_time,String board_point,String drop_point){
        ConnectionHelper help=new ConnectionHelper();

        Connection connect=help.helper();
        if(connect!=null){
            try{
                Statement statement = connect.createStatement();
                String use="use flight_reservation";
                statement.execute(use);
                String fTable="create table if not exists FlightDetails(Airlines varchar(255),Flight_Number varchar(255),Flight_Name varchar(255),Capacity int)";
                statement.execute(fTable);
                String insert_into_fTable = "INSERT INTO FlightDetails (Airlines, Flight_Number, Flight_Name, Capacity) " +
                        "VALUES ('" + airline + "','" + flight_num + "','" + flight_name + "'," + capacity + ")";
                statement.executeUpdate(insert_into_fTable);
                statement.execute(use);

                String seatTable="create table if not exists SeatDetails(Flight_Number varchar(255),Economy varchar(255),Business varchar(255),E_Cost int,B_Cost int)";
                statement.execute(seatTable);
                String insert_into_seatTable = "INSERT INTO SeatDetails (Flight_Number, Economy, Business, E_Cost, B_Cost) " +
                        "VALUES ('" + flight_num + "','" + es + "','" + bs + "'," + cost_es + ","+ cost_bs+")";
                statement.executeUpdate(insert_into_seatTable);

                //String alter_1='alter table SeatDetails'


                String TripTable="create table if not exists TripDetails(Flight_Number varchar(255),BoardTime varchar(255),DropTime varchar(255),BoardPoint varchar(255),DropPoint varchar(255))";
                statement.execute(TripTable);
                String insert_into_TripTable = "INSERT INTO TripDetails (Flight_Number, BoardTime, DropTime, BoardPoint, DropPoint) " +
                        "VALUES ('" + flight_num + "','" + board_time + "','" + drop_time + "','" + board_point + "','"+ drop_point+"')";
                statement.executeUpdate(insert_into_TripTable);


                connect.close();
            }
            catch(Exception e){
                System.out.println(e);
            }

        }


    }

    public static ResultSet getFlights(String query){
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            String use = "use flight_reservation";
            statement = getFlightConnection().createStatement();
            statement.execute(use);
            resultSet = statement.executeQuery(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;


    }

    public static Connection getFlightConnection(){
        ConnectionHelper help = new ConnectionHelper();
        Connection connect = help.helper();
        return connect;

    }

    public static void removeFlight(String flight_number){
        ConnectionHelper help=new ConnectionHelper();
        Connection connect=help.helper();
        if(connect!=null){
            try{
                Statement statement = connect.createStatement();
                String use="use flight_reservation";
                statement.execute(use);

                String remove1="delete from FlightDetails where Flight_Number=flight_number";
                statement.execute(remove1);

                String remove2="delete from SeatDetails where Flight_Number=flight_number";
                statement.execute(remove2);

                String remove3="delete from TripDetails where Flight_Number=flight_number";
                statement.execute(remove3);

                connect.close();


            }
            catch(Exception e){
                System.out.println(e);
            }
        }
    }




}
