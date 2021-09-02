package com.example.mediaplayer.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.example.mediaplayer.receivers.ActionBroadcastReceiver
import com.example.mediaplayer.receivers.ReceiverUtils
import com.example.mediaplayer.service.MusicService

class MainFragmentRepository(private val context: Context) {
    private val actionReceiver: BroadcastReceiver = ActionBroadcastReceiver()
    private val service = Intent(context, MusicService::class.java)

    fun initReceiverAndFirstNotification() {
        context.startService(service)
        context.registerReceiver(
            actionReceiver,
            IntentFilter(ReceiverUtils.ACTION_LIST)
        )
    }

    fun unregisterReceiver() {
        context.unregisterReceiver(actionReceiver)
        context.stopService(service)
    }
}