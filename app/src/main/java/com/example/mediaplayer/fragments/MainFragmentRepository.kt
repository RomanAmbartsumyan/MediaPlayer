package com.example.mediaplayer.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.example.mediaplayer.MusicService
import com.example.mediaplayer.notifications.MusicNotification
import com.example.mediaplayer.receivers.ActionBroadcastReceiver

class MainFragmentRepository(private val context: Context) {
    private val actionReceiver: BroadcastReceiver = ActionBroadcastReceiver()
    private val service = Intent(context, MusicService::class.java)

    fun initReceiverAndFirstNotification() {
        context.startService(service)
        context.registerReceiver(
            actionReceiver,
            IntentFilter(MusicNotification.ACTION_LIST)
        )
    }

    fun unregisterReceiver() {
        context.unregisterReceiver(actionReceiver)
        context.stopService(service)
    }
}