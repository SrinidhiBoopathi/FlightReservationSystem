package dao;

import java.sql.*;
import java.io.*;
import java.util.Scanner;

//public static void addFlighttoDatabase(String airline, String flight_name, String flight_num, int capacity, int es, int bs, int cost_es, int cost_bs, String board_time, String drop_time, String board_point, String drop_point) {
//
//        }
public class FlightReservationDAO {

    static final String DB_URL = "jdbc:mysql://localhost/";
    static final String USER = "guest";
    static final String PASS = "guest123";
    static final String dbname="flight_reservation";
    public static void createdBConnection() throws ClassNotFoundException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    DB_URL, "root", "1234"
            );

            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE DATABASE "+dbname);
            System.out.println("Database created successfully...");
//            while (resultSet.next()) {
//                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
//                ;
//
//            }
            connection.close();
        }catch(Exception e){
            System.out.println("Database already exists");
        }
    }



}