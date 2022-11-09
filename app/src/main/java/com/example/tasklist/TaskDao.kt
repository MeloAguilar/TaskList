package com.example.tasklist

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {


    @Query("SELECT * FROM task_entity")
    suspend fun getAll() : MutableList<TaskEntity>


    @Query("Select name From task_entity Where id =:id")
    suspend fun getTaskById(id:Int) : TaskEntity


   suspend fun updateTask(task:TaskEntity)


   suspend fun deleteTask(task:TaskEntity)
   @Insert
   suspend fun addTask(task: TaskEntity) : Int

    //@Update

   //@Delete


}