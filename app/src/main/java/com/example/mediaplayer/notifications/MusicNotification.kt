package com.example.mediaplayer.notifications

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.mediaplayer.R
import com.example.mediaplayer.objects.Track
import com.example.mediaplayer.receivers.MusicBroadcastReceiver

object MusicNotification {
    const val TRACK = "track"
    const val ACTION_LIST = "action_list"
    const val PLAY_PAUSE = "play_pause"
    const val PREVIOUS = "previous"
    const val REPEAT = "repeat"
    const val NEXT = "next"
    const val RANDOM_TRACK = "random_track"
    const val ACTION_NAME = "action_name"
    const val NOTIFICATION_ID = 1

    fun showMusicNotification(context: Context, track: Track, isPlaying: Boolean) {
        val notification = getNotification(context, track, isPlaying)
        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, notification)
    }

    fun getNotification(
        context: Context,
        track: Track,
        isPlaying: Boolean
    ): Notification {
        val pendingNextIntent = getPendingIntent(context, NEXT, track)
        val pendingPlayPauseIntent = getPendingIntent(context, PLAY_PAUSE, track)
        val pendingPreviousIntent = getPendingIntent(context, PREVIOUS, track)
        val pendingRepeatIntent = getPendingIntent(context, REPEAT, track)
        val pendingRandomTrackIntent = getPendingIntent(context, RANDOM_TRACK, track)

        val iconImage = if (isPlaying) {
            R.drawable.ic_pause
        } else {
            R.drawable.ic_play
        }

        val icon = BitmapFactory.decodeResource(context.resources, track.icon)

        return NotificationCompat.Builder(context, NotificationChannels.MUSIC_CHANNEL_ID)
            .setOnlyAlertOnce(true)
            .setOngoing(true)
            .setContentTitle(track.artist)
            .setContentText(track.name)
            .setLargeIcon(icon)
            .addAction(R.drawable.ic_repeat, REPEAT, pendingRepeatIntent)
            .addAction(R.drawable.ic_previous, PREVIOUS, pendingPreviousIntent)
            .addAction(iconImage, PLAY_PAUSE, pendingPlayPauseIntent)
            .addAction(R.drawable.ic_next, NEXT, pendingNextIntent)
            .addAction(R.drawable.ic_random, RANDOM_TRACK, pendingRandomTrackIntent)
            .setStyle(
                androidx.media.app.NotificationCompat.MediaStyle()
                    .setShowActionsInCompactView(1, 2, 3)
            )
            .setSmallIcon(R.drawable.ic_music)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
    }

    private fun getPendingIntent(context: Context, action: String, track: Track): PendingIntent {
        val intent = Intent(context, MusicBroadcastReceiver::class.java)
            .setAction(action)
            .putExtra(TRACK, track)
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }
}