package com.geektech.hw1_4m.data;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.geektech.hw1_4m.ui.home.Model.TaskModel;

import java.util.List;


@Dao
public interface TaskDao {

    @Query("SELECT * FROM `taskmodel`")
    List<TaskModel> getAllTasks();

    @Query("SELECT * FROM `taskmodel` ORDER BY title ASC")
    List<TaskModel> getAllTasksByAlphabet();

    @Query("SELECT * FROM `taskmodel` ORDER BY created ASC")
    List<TaskModel> getAllTasksByDate();

    @Insert
    void addTask(TaskModel taskModel);

    @Delete
    void deleteTask(TaskModel taskModel);

    @Update
    void update(TaskModel taskModel);
}
