package view;

import flightreservation.FlightReservation;
import service.AdminServices;
import service.Authentication;
import service.CustomerServices;

import java.util.Scanner;

public class AdminView {

    public static void login(){

        System.out.println("!!!!!!!!!!--- Welcome to admin page. Please enter admin credentials for validation ----!!!!!!!!!");
        Scanner in = new Scanner(System.in);
        System.out.println("  Enter the username:");
        String mail = in.next();
        System.out.println("  Enter the password:");
        String password = in.next();

        Authentication a=new Authentication();
        if(a.adminverify(mail, password)) {
            printAdminOptions();
        }else{
            System.out.println("please verify your credentials");
        }

    }

    public static void printAdminOptions(){
        AdminServices adservice=new AdminServices();
        Scanner in = new Scanner(System.in);
        int choice=0;

        while (choice!=4){
        System.out.println("1.Add Flights");
        System.out.println("2.Remove Flight");
        System.out.println("3.View Bookings");
        System.out.println("4.Exit");
        System.out.println("Enter a number to perform an operation");
        choice=in.nextInt();

        switch(choice) {


            case 1:
                System.out.println("Enter the airline comapany");
                String airline = in.next();
                System.out.println("Enter the Flight name");
                String flight_name = in.next();
                System.out.println("Enter the flight number");
                String flight_num = in.next();
                System.out.println("Enter the capacity");
                int capacity = in.nextInt();
                System.out.println("enter the no of e.seats");
                int es = in.nextInt();
                System.out.println("enter the no of b.seats");
                int bs = in.nextInt();
                System.out.println("enter the cost of e.seats");
                int cost_es = in.nextInt();
                System.out.println("enter the cost of b.seats");
                int cost_bs = in.nextInt();
                System.out.println("Enter the boarding time");
                String board_time = in.next();
                System.out.println("Enter the dropping time");
                String drop_time = in.next();
                System.out.println("Enter the boarding place");
                String board_point = in.next();
                System.out.println("Enter the dropping place");
                String drop_point = in.next();

                adservice.addFlight(airline, flight_name, flight_num, capacity, es, bs, cost_es, cost_bs, board_time, drop_time, board_point, drop_point);
                break;
            case 2: {
                System.out.println("Enter the flight number");
                String flight_number = in.next();
                adservice.removeFlight(flight_number);
                break;
            }
            case 3:{
                System.out.println("Enter the flight number");
                String flight_number = in.next();
                System.out.println("Enter the time");
                String time = in.next();

                adservice.viewbookings(flight_number, time);
            }
                break;
            case 4:
                FlightReservation.initialMethod();
                break;
        }}
    }
}
