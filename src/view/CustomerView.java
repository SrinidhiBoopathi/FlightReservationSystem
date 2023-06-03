package view;

import dao.CustomerDAO;
import service.Authentication;
import service.CustomerServices;

import java.util.Scanner;

public class CustomerView {
  public static void printCustomerOptions() {
       System.out.println("1.Signup");
       System.out.println("2.Login");
      Scanner in= new Scanner(System.in);
      int choice = in.nextInt();
       switch (choice) {
           case 1:
               System.out.println("Enter the first name");
               String f_name = in.next();
               System.out.println("Enter the last name");
               String l_name = in.next();
               System.out.println("Enter the e-mail");
               String mail = in.next();
               System.out.println("Enter the password:");
               String password = in.next();
               System.out.println("Enter the Gender");
               String gender = in.next();
               System.out.println("Enter the date of birth (dd/mm/yyyy)");
               String dob = in.next();
               System.out.println("Enter the aadhar number");
               String aadhar = in.next();
               System.out.println("Enter the phone number");
               String p_no = in.next();
               signup(f_name, l_name, mail, password, gender, dob, aadhar, p_no);

           case 2:
               System.out.println("=======Please enter the follwing details to login=====");
               System.out.println("Enter the e-mail");
               String maill = in.next();
               System.out.println("Enter the password:");
               String passwordd = in.next();
               login(maill, passwordd);

               break;

       }
   }

    public static void login(String mail,String password){
        Authentication a=new Authentication();
        if(a.verify(mail, password)) {
            CustomerServices cServices = new CustomerServices(mail);
            printCustomerOperations(cServices);
        }else{
            System.out.println("please verify your credentials");
        }
    }

    public static void signup(String f_name,String l_name,String mail,String password,String gender,String dob,String aadhar,String p_no){
        CustomerDAO custo=new CustomerDAO();
        custo.signup(f_name,l_name,mail,password,gender,dob,aadhar, p_no);

    }

   public static void printCustomerOperations(CustomerServices cservice){
       System.out.println("1.Search Flights");
       System.out.println("2.Book Tickets");
       System.out.println("3.My Bookings");
       System.out.println("4.Logout");
       System.out.println("Enter a number");
       Scanner in = new Scanner(System.in);
       int n = in.nextInt();
       switch(n) {
           case 1:
               System.out.println("Enter the date");
               String date = in.next();
               System.out.println("Enter the time");
               String time = in.next();
               cservice.searchFlight(date, time);
               // searchFlight();
               break;
           case 2:
               System.out.println("Enter the date");
               String datee = in.next();
               System.out.println("Enter the boarding point");
               String board = in.next();
               System.out.println("Enter the Destination");
               String dest = in.next();
               cservice.bookTickets(datee, board, dest);
               break;
           case 3:
               System.out.println("Enter the mail");
               String email = in.next();
               cservice.displayMyBooking(email);
               break;
           case 4:
               //logout
               break;
       }

   }
}
