package com.example.mediaplayer.fragments


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mediaplayer.R


class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initReceiverAndFirstNotification()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.unregisterReceiver()
    }
}