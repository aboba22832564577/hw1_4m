package com.geektech.hw1_4m.ui.home.Model;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity
public class TaskModel {


    @PrimaryKey(autoGenerate = true)
    private int id;


    private String title;
    private String created;

    @Ignore
    public TaskModel(String title, String created) {
        this.title = title;
        this.created = created;
    }

    public TaskModel(int id, String title, String created) {
        this.id = id;
        this.title = title;
        this.created = created;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getTitle() {
        return title;
    }

    public String getCreated() {
        return created;
    }
}
