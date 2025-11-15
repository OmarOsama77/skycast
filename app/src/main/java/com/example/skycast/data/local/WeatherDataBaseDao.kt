package com.example.skycast.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.skycast.data.local.entities.Weather

@Dao
interface WeatherDataBaseDao{
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertData(data: List<Weather>)
  @Query("SELECT * FROM Weather")
  suspend fun getData():List<Weather>
}