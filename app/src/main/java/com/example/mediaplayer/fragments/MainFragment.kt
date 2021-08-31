package com.example.mediaplayer.fragments


import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mediaplayer.R
import com.example.mediaplayer.notifications.MusicNotification
import com.example.mediaplayer.objects.Track
import com.example.mediaplayer.receivers.ActionBroadcastReceiver


class MainFragment : Fragment(R.layout.fragment_main) {
    private var actionReceiver: BroadcastReceiver = ActionBroadcastReceiver()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initReceiverAndFirstNotification()
    }

    private fun initReceiverAndFirstNotification() {
        requireContext().registerReceiver(
            actionReceiver,
            IntentFilter(MusicNotification.ACTION_LIST)
        )
        MusicNotification.showMusicNotification(
            requireContext(),
            Track("Artist1", "track1", R.drawable.t1, R.raw.track),
            false
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        requireContext().unregisterReceiver(actionReceiver)
    }
}