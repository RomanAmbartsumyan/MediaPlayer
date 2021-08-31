package com.example.mediaplayer.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import com.example.mediaplayer.R
import com.example.mediaplayer.notifications.MusicNotification
import com.example.mediaplayer.objects.Track

class ActionBroadcastReceiver : BroadcastReceiver() {
    private var isPlaying = false
    private var track: Track? = null
    private lateinit var mediaPlayer: MediaPlayer
    private var isLoop = false
    private var position = 0

    private val tracks =
        arrayListOf(
            Track("Artist1", "track1", R.drawable.t1, R.raw.track),
            Track("Artist2", "track2", R.drawable.t2, R.raw.track2),
        )

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.extras?.getString(MusicNotification.ACTION_NAME)

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