package com.example.skycast.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.math.round
import org.threeten.bp.format.TextStyle
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.util.Locale




@Parcelize
data class Weather(
    val time: String,
    val weatherCode: Double,
    val windSpeed:Double,
    val rain: Double,
    val snow: Double,
    val tempMax: Double,
    var fav: Boolean = false,
    val tempMin: Double,
    val day: String=calcDate(time),
    val temp: Double = round(((tempMin + tempMax) / 2) * 10) / 10


) : Parcelable{
  companion object{
      fun calcDate(time: String): String {
          return try {
              val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
              val date = LocalDate.parse(time, formatter)
              date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
          } catch (e: Exception) {
              ""
          }
      }
  }


}

