package com.example.tasklist

import androidx.room.*

@Dao
interface TaskDao {


    @Query("SELECT * FROM task_entity")
    suspend fun getAll() : MutableList<TaskEntity>


    @Query("Select * From task_entity Where id =:id")
    suspend fun getTaskById(id:Long) : TaskEntity

    @Update
   suspend fun updateTask(task:TaskEntity)

    @Delete
   suspend fun deleteTask(task:TaskEntity)
   @Insert
   suspend fun addTask(task: TaskEntity) : Long







}