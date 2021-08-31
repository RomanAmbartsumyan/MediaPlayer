package com.example.mediaplayer.app

import android.app.Application
import android.os.Build
import com.example.mediaplayer.notifications.NotificationChannels

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannels.create(this)
        }
    }
}