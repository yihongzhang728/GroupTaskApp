package com.example.grouptaskapp;

import android.app.Application;

public class MyApplication extends Application {
    private String username;
    private int numTasks;

    public String getUsername() {
        return username;
    }

    public void setUsername(String someVariable) {
        this.username = someVariable;
    }

    public int getNumTasks(){
        return numTasks;
    }

    public void setNumTasks(int someNum){
        this.numTasks = someNum;
    }
}
