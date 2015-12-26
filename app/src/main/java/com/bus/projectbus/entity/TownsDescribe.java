package com.bus.projectbus.entity;

/**
 * Created by End on 24-Dec-15.
 */
public class TownsDescribe {


    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getInfo() {
        return id+" | "+ name;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
