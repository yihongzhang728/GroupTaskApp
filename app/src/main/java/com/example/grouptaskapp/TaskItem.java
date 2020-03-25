package com.example.grouptaskapp;

public class TaskItem {

    private String name;
    private String assignee;
    private String deadline;


    TaskItem(String name, String assignee, String deadline) {
        this.setAll(name, assignee, deadline);
    }

    public String getName() { return name; }
    public String getAssignee() { return assignee; }
    public String getDeadline() { return deadline; }

    public void setAll(String name, String deadline, String assignee) {
        this.name = name;
        this.assignee = assignee;
        this.deadline = deadline;
    }
}
