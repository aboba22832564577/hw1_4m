package com.geektech.hw1_4m.ui.home.Model;



public class TaskModel {
    private final String title;
    private final String created;


    public TaskModel(String title, String created) {
        this.title = title;
        this.created = created;
    }

    public String getTitle() {
        return title;
    }

    public String getCreated() {
        return created;
    }
}
