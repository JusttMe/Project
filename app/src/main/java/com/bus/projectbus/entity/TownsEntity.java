package com.bus.projectbus.entity;

import java.util.ArrayList;

/**
 * Created by End on 24-Dec-15.
 */
public class TownsEntity {
    private ArrayList<TownsDescribe> towns;
    public ArrayList<TownsDescribe> getTowns() {
        return towns;
    }
    public String getInfo() {
        String res = "";
        for (TownsDescribe td: towns) {
            res = res + td.getInfo() +"\n";
        }
        return res;
    }
    public void setTowns(ArrayList<TownsDescribe> towns) {
        this.towns = towns;
    }




}
