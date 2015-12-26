package com.bus.projectbus.entity;

public class CarsDescribe
{


    private int id;
    private String brand;
    private int seats;

    public int getSeats() {
        return seats;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }
    public String getInfo() {
        return id+" | "+brand+" | "+seats;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

}
