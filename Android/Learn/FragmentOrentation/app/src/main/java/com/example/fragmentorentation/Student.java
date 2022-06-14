package com.example.fragmentorentation;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private int birth;
    private String address;
    private String email;

    public Student(String name, int birth, String address, String email) {
        this.name = name;
        this.birth = birth;
        this.address = address;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
