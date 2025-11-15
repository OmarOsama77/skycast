package com.example.skycast.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.skycast.data.local.entities.Weather

@Database(entities = [Weather::class], version =1 , exportSchema = false)
abstract class WeatherDataBase : RoomDatabase(){
    abstract val weatherDataBaseDao : WeatherDataBaseDao

    companion object{
        @Volatile
        private var INSTANCE : WeatherDataBase? = null
        fun getInstance(context: Context): WeatherDataBase{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        WeatherDataBase::class.java,
                        "WeatherDataBase"
                    ).fallbackToDestructiveMigration()
                        .build()
                }
                return instance
            }
        }
    }
}