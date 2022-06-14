package com.example.webservice;

public class Person {

    private int ID;
    private String name;
    private int birth;
    private String address;

    public Person(int ID, String name, int birth, String address) {
        this.ID = ID;
        this.name = name;
        this.birth = birth;
        this.address = address;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirth() {
        return birth;
    }

    public void setBirth(int birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
