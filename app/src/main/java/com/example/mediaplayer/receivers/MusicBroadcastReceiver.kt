package com.example.mediaplayer.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.mediaplayer.notifications.MusicNotification
import com.example.mediaplayer.objects.Track

class MusicBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.sendBroadcast(
            Intent(MusicNotification.ACTION_LIST)
                .putExtra(MusicNotification.ACTION_NAME, intent?.action)
                .putExtra(
                    MusicNotification.TRACK, intent?.extras?.getParcelable<Track>(
                        MusicNotification.TRACK
                    )
                )
        )
    }
}