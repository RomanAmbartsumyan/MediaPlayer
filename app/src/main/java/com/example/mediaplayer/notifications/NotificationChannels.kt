package com.example.mediaplayer.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat

object NotificationChannels {

    const val MUSIC_CHANNEL_ID = "music"

    @RequiresApi(Build.VERSION_CODES.O)
    fun create(context: Context) {
        createMassageChanel(context)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createMassageChanel(context: Context) {
        val name = "Music"
        val channelDescription = "Urgent message"
        val priority = NotificationManager.IMPORTANCE_LOW

        val channel = NotificationChannel(MUSIC_CHANNEL_ID, name, priority).apply {
            description = channelDescription
        }

        NotificationManagerCompat.from(context).createNotificationChannel(channel)
    }
}