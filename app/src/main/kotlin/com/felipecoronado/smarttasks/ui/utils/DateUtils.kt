package com.felipecoronado.smarttasks.ui.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDate
import javax.inject.Inject

class DateUtils @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun saveCurrentDate(date: LocalDate) {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("current_date", date.toString())
            apply()
        }
    }

    fun getCurrentDate(): LocalDate {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val dateString = sharedPreferences.getString("current_date", LocalDate.now().toString())
        return LocalDate.parse(dateString)
    }
}
