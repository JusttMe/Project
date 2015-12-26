package com.bus.projectbus.entity;


import com.google.gson.annotations.SerializedName;

public class UserEntity {
    @SerializedName("login")
    private String sLogin;
    @SerializedName("password")
    private String sPassword;
    @SerializedName("name")
    private String sName;
    @SerializedName("surname")
    private String sSurname;
    @SerializedName("token")
    private String sToken;


   public UserEntity(String login, String password, String name, String surname, String token) {

        sLogin = login;
        sPassword = password;
        sName = name;
        sSurname = surname;
        sToken = token;

    }
    public UserEntity(String login, String password, String name, String surname) {

        sLogin = login;
        sPassword = password;
        sName = name;
        sSurname = surname;

    }




    public String getLogin() {
        return sLogin;
    }

    public String getPassword() {
        return sPassword;
    }

    public String getName() {
        return sName;
    }

    public String getSurname() {
        return sSurname;
    }
    public String getToken() {
        return sToken;
    }
    public String getInfo() {
        String s = sLogin +" | "+ sPassword +" | "+sName+" | "+sSurname+" | "+sToken;
        return s;
    }

    public void setLogin(String login) {
        sLogin = login;
    }

    public void setPassword(String password) {
        sPassword = password;
    }

    public void setName(String name) {
        sName = name;
    }

    public void setSurname(String surname) {
        sSurname = surname;
    }
    public void setToken(String token){
        sToken = token;
    }
}