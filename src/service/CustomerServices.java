package service;

import Helper.ConnectionHelper;
import dao.BookingDAO;
import dao.CustomerDAO;
import dao.TripDAO;
import model.Customer;
import model.Flight;
import model.Seats;
import model.Trips;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class CustomerServices {

    public CustomerServices(String email){
        customer = new Customer(email);
    }
     Customer customer;
    public static void signup(String f_name,String l_name,String mail,String password,String gender,String dob,String aadhar,String p_no){
        CustomerDAO custo=new CustomerDAO();
        custo.signup(f_name,l_name,mail,password,gender,dob,aadhar, p_no);
        login(mail, password);
    }
    public static void login(String mail,String password){
        Authentication a=new Authentication();

        if(a.verify(mail,password)){
            System.out.println("correct");

        }
        else{
            System.out.println("Invalid mail or password");
        }
    }
    public static void searchFlight(String date,String time)  {
        Map<String, String> conditionMap = new HashMap<>();
        conditionMap.put("BoardTime",time);

        ResultSet resultSet = TripDAO.queryTripDetails("Flight_Number", conditionMap);

        List<Flight> flightArray = new ArrayList<Flight>();

        while (true){
            try {
                while (resultSet.next()){
                    Flight flight = new Flight();
                    String flight_num = resultSet.getString(1);
                    flight.setFlight_num(flight_num);
                    flightArray.add(flight);

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        // I need a flight at that date
        //fetch data from booking
        //get flight details of that date, display only if it has available seats
        //
        //display all flights with message, enter boarding point to check availabity



    }

    public void bookTickets(String date,String board,String dest)  {
        //ask no of seats to be booked
        //ask boarding point, dropping point, date
        //first display available seats and flights
        // choose a number from above to select a flight
        //then display economic and business as 1.Economic 2.Business, as loop for no of seats to be booked ask them to choose a number
        //when economic is selected, display the seats available
                //then ask to choose the seat number
        //use this seat number to book the ticket -> create an entry in booking table

        BookingDAO booking=new BookingDAO();
        Map<String, String> conditionMap = new HashMap<>();
        conditionMap.put("DropPoint",dest);
        conditionMap.put("BoardPoint", board);
        //this gives all the flight details present, be the seats are empty or filled
        String fields = "Flight_Number";
        String query = "Select Flight_Number,BoardTime,DropTime from TripDetails where BoardPoint='"+board+"' AND DropPoint='"+dest+"'";
        ResultSet resultSet = TripDAO.queryTripDetails(query, conditionMap );
        System.out.println("1111");
        //run a loop on every flight with given date condition, if this flight is present in table, check for current available seats
        //if this flight is not present in the table, it means all the seats are available
        List<String> list=new ArrayList<>();
        //Todo
        //values for seat in a map
        try {
            List<String> allFlights = new ArrayList<>();

            while(resultSet.next()){
                try {
                    String fname=resultSet.getString("Flight_Number");
                    //list.add(s);
                    allFlights.add(fname);
                    System.out.println("Flight number:"+fname);
                    String countQuery="select Flight_Number,seat_no,time from book_table where Flight_Number='"+fname+"'";
                    ResultSet resultSetCount =TripDAO.queryTripDetails(countQuery,conditionMap);
                    System.out.println(resultSetCount.getFetchSize());
                    //get cost and count from seat tables

                        System.out.println("inside flight present in booking table");
                        Map<String,List<String>> currentDateFlights = new HashMap<>();


                        while (resultSetCount.next()) {

                            String flightName = resultSetCount.getString(1);
                            String seatNo = resultSetCount.getString(2);
                           // Trips trips = new Trips();
                            String tripTime = resultSetCount.getString(3);
                            //trips.setBoardingTime(tripTime);
                            allFlights.remove(flightName);
                            String flightKey = flightName+"_"+tripTime;
                            System.out.println("flight name:"+flightName+" tripTime"+tripTime+" seat No."+seatNo);
                            if(currentDateFlights.containsKey(flightKey)){
                                List<String> seatsList = currentDateFlights.get(flightKey);
                                seatsList.add(seatNo);
                                currentDateFlights.put(flightKey, seatsList);
                            }else{
                                List<String> seatsList = new ArrayList<>();
                                seatsList.add(seatNo);
                                currentDateFlights.put(flightKey, seatsList);
                            }

                        }
                        System.out.println("The following flights are available for your given boarding and given date");
                        printAvailableSeats(currentDateFlights);
                        this.bookTicketsNow(currentDateFlights,date,board,dest);



                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            for(String flightNumber:allFlights) {

                    System.out.println("Flight Details");
                    System.out.println("Flight number:" + flightNumber);
                    System.out.println("Total_availabe=60");

                    // ToDo:e_cost and b_cost
                    //  System.out.println("Eco_availabe=30"+" Cost="+e_cost+" Seats="+e_list);
                    //  System.out.println("Business_availabe=30"+" Cost="+b_cost+" Seats="+b_list);
                //TOdo: print flight details of same number with different timings
             }



            }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }









    }
    public void bookTicketsNow(Map<String, List<String>> currentDateFlights,String date,String board,String dest){
      //String this.getEmail();
        BookingDAO bookingDAO=new BookingDAO();
      String email = this.customer.getEmail();
      Scanner in = new Scanner(System.in);
      //check last
      //System.out.println("Select one of the flight listed above");
      System.out.println("Enter the flight number from above list");
      String flight_number = in.next();
      System.out.println("Enter the trip timing");
      String trip_timing = in.next();
        System.out.println("Enter the no. of tickets needed");
        int head_count = in.nextInt();
        String current_flight = flight_number+"_"+trip_timing;
        List<String> occupiedSeats = currentDateFlights.get(current_flight);
        if(occupiedSeats==null){
            System.out.println("please enter the correct value");
        }

        String economic_cost = "0";
        String business_cost = "0";

        String query = "select E_Cost, B_Cost from seatdetails where Flight_Number='"+flight_number+"'";
        try {


            Statement statement = ConnectionHelper.helper().createStatement();

            String use = "use flight_reservation";
            statement.execute(use);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                economic_cost = resultSet.getString(1);
                business_cost = resultSet.getString(2);

            }
        } catch (SQLException e) {

            System.out.println(e);
        }
//        int eco_cost = Integer.parseInt(economic_cost)*head_count;
//        int bus_cost=Integer.parseInt(business_cost)*head_count;
        int totalcost=0;
        for(int i=0;i<head_count; i++){
            System.out.println("=====please enter the seat type====\n1.Economic seats\n2.Business seats");
            int seat_type = in.nextInt();
            System.out.println("Please select a seat from above availability details.");
            String seat_selected = in.next();
            if(seat_selected.equals(1)){
                totalcost+=Integer.parseInt(economic_cost);
            }
            else{
                totalcost+=Integer.parseInt(business_cost);
            }
            System.out.println("Selected seat:"+seat_selected);
            if(occupiedSeats.contains(""+seat_selected)){
                System.out.println("please select a valid seat, this seat is taken");
            }else{
                occupiedSeats.add(""+seat_selected);
                currentDateFlights.put(current_flight,occupiedSeats);
                //bookingSeat(current_flight, seat_selected, email);

                bookingDAO.insertBookingDetail(current_flight,String.valueOf(seat_selected),email,board,dest,date);

            }

        }
        System.out.println(totalcost);




    }


    public void BookSeat(String current_flight, String seat, String email){

    }

    private static void printAvailableSeats(Map<String, List<String>> currentDateFlights) {
        fill();
        for(Map.Entry<String, List<String>> entry: currentDateFlights.entrySet() ){
            List<String> occupiedSeats = entry.getValue();

         for(int j=0;j<occupiedSeats.size();j++) {
            //temp.add(value);
             String seat_no = occupiedSeats.get(j);
            if(e_list.contains(seat_no)){
                e_list.set(Integer.parseInt(seat_no)-1,"-");
            }
            else if(b_list.contains(seat_no)){
                b_list.set(Integer.parseInt(seat_no)-1-30,"-");
            }


         }

            String[] flightDetails = entry.getKey().split("_");
            System.out.println("Flight number:"+flightDetails[0]+"    Trip timimgs: "+flightDetails[1]);
            System.out.println("========================================================================");
            System.out.println("======Available Economic seats=======");
            System.out.println(e_list);
            System.out.println("======Available Business seats=======");
            System.out.println(b_list);
            fill();


        }}
    public static void fill(){
        e_list=new ArrayList<>();
        b_list=new ArrayList<>();
        for(int i=1;i<=30;i++){
            e_list.add(String.valueOf(i));
        }
        for(int i=31;i<=60;i++){
            b_list.add(String.valueOf(i));
        }
    }
    static List<String> e_list=new ArrayList<>();
    static List<String> b_list=new ArrayList<>();

    public static void displayMyBooking(String email){

    }
}
