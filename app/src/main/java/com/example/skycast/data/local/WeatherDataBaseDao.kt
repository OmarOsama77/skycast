package com.example.skycast.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.skycast.data.local.entities.WeatherEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDataBaseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(data: List<WeatherEntity>)

    @Query("SELECT * FROM Weather ORDER BY time ASC")
    suspend fun getData(): List<WeatherEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFav(weatherEntity: WeatherEntity)

    @Query("SELECT * FROM Weather where fav = 1")
    fun getFav(): Flow<List<WeatherEntity>>?

    @Query("Select fav From Weather where time = :time")
    suspend fun getFavStatus(time: String): Boolean
    @Query("DELETE FROM Weather WHERE time < :today")
    suspend fun deleteOldData(today: String)

}