package service;

import dao.BookingDAO;
import dao.FlightDAO;
import dao.FlightReservationDAO;

import java.util.HashMap;

public class AdminServices {

    public static void login(String mail,String password){
        Authentication a=new Authentication();

        if(a.verify(mail,password)){

        }
        else{
            System.out.println("Invalid mail or password");
        }
    }

    public static void addFlight(String airline,String flight_name,String flight_num,int capacity,int es,int bs,int cost_es,int cost_bs,String board_time,String drop_time,String board_point,String drop_point
    ){
        //String airline,String flight_name,String flight_num,int capacity,int es,int bs,int cost_es,int cost_bs,String board_time,String drop_time,String board_point,String drop_point
        FlightDAO fr=new FlightDAO();
       // fr.insertFlight("abc","def","hgj",8,8,9,4,5,"56","j","2","4");
        fr.insertFlight(airline,flight_name,flight_num,capacity,es,bs,cost_es,cost_bs,board_time,drop_time,board_point,drop_point);



    }
    public static void removeFlight(String flight_number){
        FlightDAO fr=new FlightDAO();
        fr.removeFlight(flight_number);
    }
    public static void viewbookings(String flight_nu){


    }

}
