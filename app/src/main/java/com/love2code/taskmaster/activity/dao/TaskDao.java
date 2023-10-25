package com.love2code.taskmaster.activity.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.love2code.taskmaster.activity.model.Tasks;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    public void insertATask(Tasks task);


    @Query("select * from tasks")
    public List<Tasks> findAll();

    @Query("select * from Tasks ORDER BY title ASC")
    public List<Tasks> findAllSortedByName();

    @Query("select * from Tasks where id = :id")
    Tasks findByAnId(long id);






}
