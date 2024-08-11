package com.motivation.inspiria.core.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class DateTimeChangeReceiver(private val onDateTimeChange: () -> Unit) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        onDateTimeChange()
    }
}