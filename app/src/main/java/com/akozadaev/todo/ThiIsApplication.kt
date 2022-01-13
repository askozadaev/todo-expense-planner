package com.akozadaev.todo

import android.app.Application
import com.akozadaev.todo.room.AppDatabase
import androidx.room.Room




class ThiIsApplication : Application() {

    companion object {
        var instance: ThiIsApplication? = null
    }

    private var database: AppDatabase? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
            .allowMainThreadQueries()
            .build()
    }

    fun getDatabase(): AppDatabase? {
        return database
    }

}