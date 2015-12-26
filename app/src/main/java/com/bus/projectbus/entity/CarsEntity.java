package com.bus.projectbus.entity;

import java.util.ArrayList;

/**
 * Created by End on 24-Dec-15.
 */
public class CarsEntity {




    private ArrayList<CarsDescribe> cars;
    public ArrayList<CarsDescribe> getCars() {
        return cars;
    }
    public void setCars(ArrayList<CarsDescribe> cars) {
        this.cars = cars;
    }
    public String getInfo() {
        String res = "";

        for (CarsDescribe cd: cars) {
            res = res + cd.getInfo()+"\n";
        }

        return res;
    }
}
