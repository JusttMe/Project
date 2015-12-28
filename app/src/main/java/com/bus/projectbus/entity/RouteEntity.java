package com.bus.projectbus.entity;


import java.util.ArrayList;

public class RouteEntity {

    private String from;
    private String to;
    private int tripNumber;
    private CarsDescribe car; //TODO not ArrayList<CarsDescribe> . just CarsDescribe
    private ArrayList<Integer> takenSeats;

    public RouteEntity(String from, String to, int tripNumber) {
        this.from = from;
        this.to = to;
        this.tripNumber = tripNumber;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getTripNumber() {
        return tripNumber;
    }

    public CarsDescribe getCar() {
        return car;
    }

    public ArrayList<Integer> getTakenSeats() {
        return takenSeats;
    }
    public String getInfo() {
        String res = "";
        String buf1 = "";
        String buf2 = "";
        res = from+" || "+to+" || "+tripNumber+" [ ";

            buf1 = buf1 + car.getInfo()+" | ";
                res = res+buf1+" ] || [ ";
        for (Integer in: takenSeats) {
            buf2 = buf2 + in+" | ";
        }
        res = res + buf2+" ]";
        return res;
    }


    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setTripNumber(int tripNumber) {
        this.tripNumber = tripNumber;
    }

    public void setCar(CarsDescribe car) {
        this.car = car;
    }

    public void setTakenSeats(ArrayList<Integer> takenSeats) {
        this.takenSeats = takenSeats;
    }
}