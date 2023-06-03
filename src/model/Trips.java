package model;

import java.util.List;

public class Trips {
    public String getBoardingTime() {
        return boardingTime;
    }

    public void setBoardingTime(String boardingTime) {
        this.boardingTime = boardingTime;
    }

    public String getDroppingTime() {
        return droppingTime;
    }

    public void setDroppingTime(String droppingTime) {
        this.droppingTime = droppingTime;
    }

    public String getBoardingPoint() {
        return boardingPoint;
    }

    public void setBoardingPoint(String boardingPoint) {
        this.boardingPoint = boardingPoint;
    }

    public String getDroppingPoint() {
        return droppingPoint;
    }

    public void setDroppingPoint(String droppingPoint) {
        this.droppingPoint = droppingPoint;
    }

    String boardingTime;
    String droppingTime;
    String boardingPoint;
    String droppingPoint;

    List<Seats> tripSeats;

    public List<Seats> getTripSeats() {
        return tripSeats;
    }

    public void setTripSeats(List<Seats> tripSeats) {
        this.tripSeats = tripSeats;
    }
}
