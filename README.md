# FlightReservationSystem

The Flight Ticket Booking application is a console application made using Java(JDBC) and MySql.
I used IntelliJ create this application. It provides functionalities like booking flight tickets, view the flight details.

The program also creates 5 tables in that database named Customer details,Flight details,
Seat details,Trip details,Booking details. 

• The default admin username is "admin" and password is "123".

The console application has two users:

Type of Users:

• Admin

• Users

ADMIN USE CASES:

• Login

• Add Flights

• Remove Flights


USER USE CASES:

• User Sign-up

• User Login

• Searching for flights based on date and time

• Booking tickets on a flight based on availability

• Logout

HOW TO RUN THIS APPLICATION?

• Install Java and MySql

• Install a Java IDE (Eclipse, NetBeans, Intellij etc)

• Install mysql-connector-java.jar file to connect Java with MySql

• Create a Java project with a class named “FlightReservation”.

• Add mysql-connector-java.jar as referenced library to the Java project

• Copy this project and open it in any IDE

• Run the main class -flightreservation.java , this opens the application

This project majorly has 5 layers:

DAO:
Represents the data used in the application. This folder consists of files where the crud operations takes place majorly

Service layer:
This layer majorly handles all the business logic 

View:
This layer handles the part which gets input from the user and calls service layer files for handling business logic

Model:
This layer consists of java classes which represents an real time entity, no methods that handles major logic are present here.

Helper:
This contains all the methods that are used across the project and has no specific business logic, but can be used by other methods
