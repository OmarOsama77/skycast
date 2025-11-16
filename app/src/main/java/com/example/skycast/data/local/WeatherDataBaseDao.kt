package com.example.skycast.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.skycast.data.local.entities.Weather
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDataBaseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(data: List<Weather>)

    @Query("SELECT * FROM Weather")
    suspend fun getData(): List<Weather>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFav(weather: Weather)

    @Query("SELECT * FROM Weather where fav = 1")
     fun getFav(): Flow<List<Weather>>?
     @Query("Select fav From Weather where time = :time")
    suspend fun getFavStatus(time : String): Boolean
}