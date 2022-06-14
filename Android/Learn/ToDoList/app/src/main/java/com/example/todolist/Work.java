package com.example.todolist;

public class Work {

    private int workID;
    private String workName;

    public Work(int workID, String workName) {
        this.workID = workID;
        this.workName = workName;
    }

    public int getWorkID() {
        return workID;
    }

    public void setWorkID(int workID) {
        this.workID = workID;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }
}
