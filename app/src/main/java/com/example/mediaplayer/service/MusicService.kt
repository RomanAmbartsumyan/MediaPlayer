package com.example.mediaplayer.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.mediaplayer.notifications.MusicNotification
import com.example.mediaplayer.objects.Tracks

class MusicService : Service() {
    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification = MusicNotification.getNotification(
            this,
            Tracks.trackList[0],
            false
        )
        startForeground(MusicNotification.NOTIFICATION_ID, notification)
        return START_NOT_STICKY
    }
}