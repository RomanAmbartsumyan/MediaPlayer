package com.example.mediaplayer

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.mediaplayer.notifications.MusicNotification
import com.example.mediaplayer.objects.Track

class MusicService : Service() {
    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        val notification = MusicNotification.getNotification(
            this,
            Track("Artist1", "track1", R.drawable.t1, R.raw.track),
            false
        )
        startForeground(MusicNotification.NOTIFICATION_ID, notification)
    }
}