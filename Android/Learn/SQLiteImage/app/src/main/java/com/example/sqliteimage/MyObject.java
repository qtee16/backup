package com.example.sqliteimage;

public class MyObject {
    private int ID;
    private String name;
    private String desc;
    private byte[] image;

    public MyObject(int ID, String name, String desc, byte[] image) {
        this.ID = ID;
        this.name = name;
        this.desc = desc;
        this.image = image;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
