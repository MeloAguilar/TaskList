package com.example.tasklist

import android.app.Application
import androidx.room.Room

class MisNotasApp : Application() {
    companion object{
        lateinit var database : TaskDataBase
    }


    override fun onCreate() {
        super.onCreate()
        database =
            Room.databaseBuilder(
                this,
                TaskDataBase::class.java,
                "tasks-db"
            ).build()
    }
}