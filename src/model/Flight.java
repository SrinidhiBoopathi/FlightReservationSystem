package model;

import java.util.List;

public class Flight {
    public String getAirline() {
        return Airline;
    }

    public void setAirline(String airline) {
        Airline = airline;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "Airline='" + Airline + '\'' +
                ", flight_num='" + flight_num + '\'' +
                ", flight_name='" + flight_name + '\'' +
                ", capacity=" + capacity +
                ", seats=" + seats +
                ", trips=" + trips +
                '}';
    }

    String Airline;

    public String getFlight_num() {
        return flight_num;
    }

    public void setFlight_num(String flight_num) {
        this.flight_num = flight_num;
    }

    public String getFlight_name() {
        return flight_name;
    }

    public void setFlight_name(String flight_name) {
        this.flight_name = flight_name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Seats getSeats() {
        return seats;
    }

    public void setSeats(Seats seats) {
        this.seats = seats;
    }

    public Trips getTrips() {
        return trips;
    }

    public void setTrips(Trips trips) {
        this.trips = trips;
    }

    String flight_num;
    String flight_name;
    int capacity;
    Seats seats;
    Trips trips;

    public List<Seats> getSeatsList() {
        return seatsList;
    }

    public void setSeatsList(List<Seats> seatsList) {
        this.seatsList = seatsList;
    }

    List<Seats> seatsList;


}
