package com.example.vitameanshospitaldoctor.data

import androidx.room.TypeConverter
import java.util.*

class Converters {


    @TypeConverter fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter fun DatestampToCalendar(value: Long): Calendar = Calendar.getInstance().apply { timeInMillis = value }
}