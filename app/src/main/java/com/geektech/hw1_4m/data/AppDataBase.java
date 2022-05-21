package com.geektech.hw1_4m.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.geektech.hw1_4m.ui.home.Model.TaskModel;

@Database(entities = {TaskModel.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract TaskDao caseDao();
}
