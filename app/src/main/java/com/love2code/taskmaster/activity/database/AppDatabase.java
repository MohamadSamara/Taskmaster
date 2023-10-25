package com.love2code.taskmaster.activity.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.love2code.taskmaster.activity.dao.TaskDao;
import com.love2code.taskmaster.activity.model.Tasks;

@Database(entities = {Tasks.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();

}
