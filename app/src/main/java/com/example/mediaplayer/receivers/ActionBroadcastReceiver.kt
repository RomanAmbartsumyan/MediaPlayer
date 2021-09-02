package com.example.mediaplayer.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import com.example.mediaplayer.notifications.MusicNotification
import com.example.mediaplayer.objects.Track
import com.example.mediaplayer.objects.Tracks

class ActionBroadcastReceiver : BroadcastReceiver() {
    private var isPlaying = false
    private var track: Track? = null
    private lateinit var mediaPlayer: MediaPlayer
    private var isLoop = false
    private var position = 0

    private val tracks = Tracks.trackList


    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.extras?.getString(ReceiverUtils.ACTION_NAME)

        val trackFromIntent = intent.extras?.getParcelable<Track>(MusicNotification.TRACK)!!

        if (track?.name != trackFromIntent.name) {
            mediaPlayer = MediaPlayer.create(context, trackFromIntent.res)
            if (isPlaying) {
                mediaPlayer.start()
            }
            track = trackFromIntent
        }

        when (action) {
            MusicNotification.PLAY_PAUSE -> {
                if (isPlaying) {
                    mediaPlayer.pause()
                    isPlaying = false
                    MusicNotification.showMusicNotification(context, tracks[position], isPlaying)
                } else {
                    mediaPlayer.start()
                    isPlaying = true
                    MusicNotification.showMusicNotification(context, tracks[position], isPlaying)
                }
            }
            MusicNotification.NEXT -> {
                if (tracks.size - 1 > position) {
                    position++
                    refreshTrack(context)
                }
                MusicNotification.showMusicNotification(context, tracks[position], isPlaying)
            }
            MusicNotification.PREVIOUS -> {
                if (0 < position) {
                    position--
                    refreshTrack(context)
                }
                MusicNotification.showMusicNotification(context, tracks[position], isPlaying)
            }
            MusicNotification.REPEAT -> {
                if (!isLoop) {
                    isLoop = true
                    mediaPlayer.isLooping = isLoop
                } else {
                    isLoop = false
                    mediaPlayer.isLooping = isLoop
                }
            }
            MusicNotification.RANDOM_TRACK -> {
                position = (0 until tracks.size).random()
                val track = tracks[position]
                MusicNotification.showMusicNotification(context, track, isPlaying)
            }
        }
        mediaPlayer.setOnCompletionListener {
            isPlaying = false
            MusicNotification.showMusicNotification(context, tracks[position], isPlaying)
        }
    }

    private fun refreshTrack(context: Context) {
        track = tracks[position]
        if (isPlaying) {
            mediaPlayer.stop()
            mediaPlayer = MediaPlayer.create(context, track!!.res)
            mediaPlayer.isLooping = isLoop
            mediaPlayer.start()
        } else {
            mediaPlayer = MediaPlayer.create(context, track!!.res)
        }
    }
}