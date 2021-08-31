package com.example.mediaplayer.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class MainFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MainFragmentRepository(application)

    fun initReceiverAndFirstNotification() {
        repository.initReceiverAndFirstNotification()
    }

    fun unregisterReceiver() {
        repository.unregisterReceiver()
    }
}