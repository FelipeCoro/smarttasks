package com.felipecoronado.smarttasks

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SmartTasksApplication:Application() {
    override fun onCreate() {
        super.onCreate()
    }
}