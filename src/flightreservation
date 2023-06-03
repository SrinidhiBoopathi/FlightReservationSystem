/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package flightreservation;
import dao.FlightReservationDAO;
import service.AdminServices;
import service.CustomerServices;
import view.AdminView;
import view.CustomerView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author srini
 */
public class FlightReservation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        // TODO code application logic here
        FlightReservationDAO flightdb=new FlightReservationDAO();
        flightdb.createdBConnection();
        int choice = 0;
        while(choice!=3) {
            choice = initialMethod();
            switch (choice) {

                case 1:
                    AdminView.login();
                    break;
                case 2:
                    CustomerView.printCustomerOptions();
                    break;


            }
        }
    }

    public static int initialMethod(){
        System.out.println("   Please choose your role");
        System.out.println("         1.Admin");
        System.out.println("         2.Customer");
        System.out.println("         3.Exit");
        Scanner in=new Scanner(System.in);
        System.out.println("Enter the number");
        int n=in.nextInt();
        return n;
    }


}
