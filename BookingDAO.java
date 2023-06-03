package dao;

import Helper.ConnectionHelper;

import java.sql.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.Arrays;

public class BookingDAO {
    static List<String> resultList = new ArrayList<>();
    //static int[] result1 = IntStream.rangeClosed(1, 30).toArray();
    //static List<int[]> e_list = Arrays.asList(result1);
    static List<Integer> temp=new ArrayList<>();
    static List<String> e_list=new ArrayList<>();
    static List<String> b_list=new ArrayList<>();
    public static void fill(){
        for(int i=1;i<=30;i++){
            e_list.add(String.valueOf(i));
        }
        for(int i=31;i<=60;i++){
            b_list.add(String.valueOf(i));
        }
    }



    public static void booking(String booking_id,String Flight_Number,String board,String drop,String email,String date,String time,String seat_no) {
        ConnectionHelper help=new ConnectionHelper();
        Connection connectt=help.helper();
        if(connectt!=null) {
            try {
                Statement statement = connectt.createStatement();
                String use = "use flight_reservation";
                statement.execute(use);

                String btable="create table if not exists book_table(booking_id varchar(225),Flight_Number varchar(225), board varchar(225), drop varchar(225), email varchar(225),date varchar(225), time varchar(225), seat_no varchar(225))";
                statement.executeUpdate(btable);

                String query="INSERT INTO book_table (booking_id,Flight_Number,board,drop,email,date,time,seat_no,) " +
                        "VALUES ('" + booking_id + "','" + Flight_Number + "','" + board + "','" + drop + "','" + email + "','" + date + "','" + time + "','"+ seat_no +"')";
                statement.executeUpdate(query);

                //System.out.println("list="+e_list);
                if(e_list.contains(seat_no)){
                    e_list.remove(seat_no);
                }
                else if(b_list.contains(seat_no)){
                    b_list.remove(seat_no);
                }

                connectt.close();
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
    }
    public static void  availableFlights(String date,String board,String dest) {
        ConnectionHelper help = new ConnectionHelper();
        Connection connectt = help.helper();

        String f_no= "";
        if (connectt != null) {
            try {
                Statement statement = connectt.createStatement();
                String use = "use flight_reservation";
                statement.execute(use);


                //String query="select book_table.Flight_Number FROM book_table JOIN TripDetails ON book_table.Flight_Number = TripDetails.Flight_Number where TripDetails.BoardPoint='"+board+"' and   TripDetails.DropPoint='"+dest+"' and date='"+date+"' ";
                String query="select Flight_Number from TripDetails where boardtime='"+board+"'";
                ResultSet resultSet = statement.executeQuery(query);


                while (resultSet.next()) {
                    String value = resultSet.getString("Flight_Number");

                    resultList.add(value);
                }
                System.out.println("list="+resultList);
            }
            catch(Exception e){
                System.out.println(e);
            }

        }

    }
    public static void checkingBookingTable(){
        ConnectionHelper help = new ConnectionHelper();
        Connection connectt = help.helper();


        String f_no= "";
        if (connectt != null) {
            try {
                Statement statement = connectt.createStatement();
                String use = "use flight_reservation";
                statement.execute(use);
                String board="";
                String query="select Flight_Number from TripDetails where BoardPoint='"+board+"'";
                //resultList = FlightDAO.getFlights("");
                if(resultList.isEmpty()){
                    System.out.println("No flights available for this location");
                    return;
                }
                fill();
                SeatingDAO seatingDAO=new SeatingDAO();
                int count=0;

                for(int i=0;i<resultList.size();i++){
                    //String ecost=
                    String fnum=resultList.get(i);
                    int e_cost=0;
                    int b_cost=0;
                    ResultSet resultSet=seatingDAO.querySeatDetails("E_Cost",fnum);
                    while(resultSet.next()){
                        e_cost=resultSet.getInt(1);
                    }

                    resultSet=seatingDAO.querySeatDetails("B_Cost",fnum);
                    while(resultSet.next()){
                        b_cost=resultSet.getInt(1);
                    }

                    query="select count(Flight_Number) from book_table where Flight_Number='"+fnum+"'";
                    resultSet = statement.executeQuery(query);
                    while(resultSet.next()){
                        count=resultSet.getInt(1);
                        if(count==0){
                            System.out.println("Flight Details");
                            System.out.println("Flight number:"+fnum);
                            System.out.println("Total_availabe=60");
                            System.out.println("Eco_availabe=30"+" Cost="+e_cost+" Seats="+e_list);
                            System.out.println("Business_availabe=30"+" Cost="+b_cost+" Seats="+b_list);
                        }
                     }

                }


            }
            catch(Exception e){
                System.out.println(e);
            }
        }
    }
    public static void bookingdetails(){
        ConnectionHelper help = new ConnectionHelper();
        Connection connectt = help.helper();

        String f_no= "";
        if (connectt != null) {
            try {
                Statement statement = connectt.createStatement();
                String use = "use flight_reservation";
                statement.execute(use);


               // String query="select book_table.Flight_Number FROM book_table JOIN TripDetails ON book_table.Flight_Number = TripDetails.Flight_Number where TripDetails.BoardPoint='"+board+"' and   TripDetails.DropPoint='"+dest+"' and date='"+date+"' ";

            }
            catch(Exception e){
                System.out.println(e);
            }

        }


            }

    public static void  availableSeats(String date,String board,String dest) {
        checkingBookingTable();
        int total_count=0;
        int e_total=0;
        int b_total=0;
        int e_cost=0;
        int b_cost=0;
        ConnectionHelper help = new ConnectionHelper();
        Connection connectt = help.helper();

        String f_no = "";
        if (connectt != null) {
            try {
                fill();
                if(resultList.isEmpty()){
                    System.out.println("No flights available for this location");
                    return;
                }
                Statement statement = connectt.createStatement();
                String use = "use flight_reservation";
                statement.execute(use);

                for(int i=0;i<resultList.size();i++){
                    String fname=resultList.get(i);
                    String query1="select count(*) from book_table where Flight_Number='"+fname+"'";
                    ResultSet resultSet1 = statement.executeQuery(query1);
                    while(resultSet1.next()){
                        total_count=resultSet1.getInt(1);
                    }

                    String query2="select count(*) from book_table where Flight_Number='"+fname+"' and seat_no between 1 and 30";
                    ResultSet resultSet2 = statement.executeQuery(query2);
                    while(resultSet2.next()){
                        e_total=resultSet2.getInt(1);
                    }

                    String query3="select count(*) from book_table where Flight_Number='"+fname+"' and seat_no between 31 and 60";
                    ResultSet resultSet3 = statement.executeQuery(query3);
                    while(resultSet3.next()){
                        b_total=resultSet3.getInt(1);
                    }

                    String query4="select E_Cost from SeatDetails where Flight_Number='"+fname+"'";
                    ResultSet resultSet4 = statement.executeQuery(query4);
                    while(resultSet4.next()){
                        e_cost=resultSet4.getInt(1);
                    }

                    String query5="select B_Cost from SeatDetails where Flight_Number='"+fname+"'";
                    ResultSet resultSet5 = statement.executeQuery(query5);
                    while(resultSet5.next()){
                        b_cost=resultSet5.getInt(1);
                    }

                    String query6="select seat_no from book_table where Flight_Number='"+fname+"'";
                    ResultSet resultSet = statement.executeQuery(query6);
                    while (resultSet.next()) {
                        int value = resultSet.getInt("seat_no");

                        //temp.add(value);
                        if(e_list.contains(String.valueOf(value))){
                            e_list.set(value-1,"-");
                        }
                        else if(b_list.contains(String.valueOf(value))){
                            b_list.set(value-1,"-");
                        }

                    }
                    //System.out.println("temp"+temp);




                    System.out.println("Total_availabe="+(60-total_count));
                    System.out.println("Eco_availabe="+(30-e_total)+" Cost="+e_cost+" Seats="+e_list);
                    System.out.println("Business_availabe="+(30-b_total)+" Cost="+b_cost+" Seats="+b_list);


                }
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void checkBookingTable(){
        //
        //this doesnt handle the ones which are not present in booking table,
        // so first go to trip table get flights details first, then check availabily
    }


    public static Connection getBookingConnection(){
        ConnectionHelper help = new ConnectionHelper();
        Connection connect = help.helper();

        return connect;

    }

    public static ResultSet queryBookingDetails(String queryField, Map<String,String> conditionMap){
        String query="select "+queryField+" from book_table where ";

        for (Map.Entry<String,String> entry : conditionMap.entrySet()){
            query +=  entry.getKey()+"='"+entry.getValue()+"'";
        }
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            String use = "use flight_reservation";
            Connection connection = getBookingConnection();
            statement = connection.createStatement();
            statement.execute(use);
            resultSet = statement.executeQuery(query);
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public static void insertBookingDetail(String flightDetails, String seat, String email,String board,String drop,String date){
        String[] flightDetailsSPlit = flightDetails.split("_");

        String flight_number = flightDetailsSPlit[0];
        String trip_timing= flightDetailsSPlit[1];

        String query = "insert into book_table (booking_id, Flight_Number, board, dropping, email, date, time, seat_no) values ('4', '" + flight_number + "', '" + board + "', '" + drop + "', '" + email + "', '" + date + "', '" + trip_timing + "', '" + seat + "')";

        // ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connect= ConnectionHelper.helper();
        try{
            Statement statement = connect.createStatement();
            String use = "use flight_reservation";
            statement.execute(use);
            statement.executeUpdate(query);
            System.out.println("Seats has been successfully allocated");

        }catch (Exception e){
            System.out.println("There seems a problem booking a ticket, please try again later"+e);
        }
    }




}


