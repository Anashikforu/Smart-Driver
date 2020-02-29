package com.example.smartdrive;

public class DriverDetails {


    private String name;
    private String phoneNumber;
    private String location;
    private String age;
    private String vehicle;

    public DriverDetails() {
        // This is default constructor.
    }

    public String getName() {

        return name;
    }

    public void setName(String Name) {

        this.name = Name;
    }

    public String getPhoneNumber() {

        return phoneNumber;
    }

    public void setPhoneNumber(String Phonenumber) {

        this.phoneNumber = Phonenumber;
    }

    public String getLocation(){
        return location;
    }
    public void setLocation(String Location){
        this.location=Location;
    }
    public String getAge(){
        return age;
    }
    public void setAge(String Age){
        this.age=Age;
    }

    public String getVehicle(){
        return vehicle;
    }
    public void setVehicle(String Vehicle){
        this.vehicle=Vehicle;
    }
}