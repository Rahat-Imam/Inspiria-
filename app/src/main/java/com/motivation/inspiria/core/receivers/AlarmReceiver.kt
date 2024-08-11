package com.motivation.inspiria.core.receivers

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.motivation.inspiria.R
import com.motivation.inspiria.core.ConstantOfApp.ACTION_DAILY_MOTIVATION_REMINDER
import com.motivation.inspiria.core.ConstantOfApp.DAILY_MOTIVATION_NOTIFICATION_CHANNEL
import com.motivation.inspiria.core.ConstantOfApp.DAILY_MOTIVATION_NOTIFICATION_CHANNEL_ID
import com.motivation.inspiria.core.ConstantOfApp.dailyMotivationReminderId
import com.motivation.inspiria.core.notificationManager
import com.motivation.inspiria.core.utils.SdkHelper
import com.motivation.inspiria.core.utils.alarmManager.AlarmSchedulerImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class AlarmReceiver: BroadcastReceiver(), KoinComponent{

    val alarmScheduler : AlarmSchedulerImpl by inject()

    override fun onReceive(context: Context?, intent: Intent?) {
        CoroutineScope(Dispatchers.IO).launch {
            intent?.action?.let {
                context?.let { context ->
                    if (SdkHelper.isO()) context.notificationManager.createNotificationChannel(
                        NotificationChannel(
                            DAILY_MOTIVATION_NOTIFICATION_CHANNEL_ID,
                            DAILY_MOTIVATION_NOTIFICATION_CHANNEL,
                            NotificationManager.IMPORTANCE_HIGH
                        ).apply {
                            description = context.getString(R.string.channel_description)
                        }
                    )
                }
                val message = intent.getStringExtra("message")

                when (it) {
                    ACTION_DAILY_MOTIVATION_REMINDER -> {
                        context?.let { dailyMotivationNotification(it, message!!) }
//                        alarmScheduler.scheduleAlarm(
//                            LocalDateTime.now().nextDayNoon(),
//                            ACTION_DAILY_MOTIVATION_REMINDER,
//                            dailyMotivationReminderId
//                        )
                    }
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun dailyMotivationNotification(context: Context, message:String) {

        createLargeNotification(
            context = context,
            title = "Inspiria",
            contentTitle = "Motivation",
            bigText = message,
            notificationId = dailyMotivationReminderId
        )
    }

    @SuppressLint("MissingPermission")
    private fun createLargeNotification(
        context: Context,
        title: String,
        contentTitle: String,
        bigText: String,
        notificationId: Int
    ) {
        if (SdkHelper.isO()) context.notificationManager.createNotificationChannel(
            NotificationChannel(
                DAILY_MOTIVATION_NOTIFICATION_CHANNEL_ID,
                DAILY_MOTIVATION_NOTIFICATION_CHANNEL,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = context.getString(R.string.channel_description)
            }
        )

        val builder = NotificationCompat.Builder(context, DAILY_MOTIVATION_NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_info_outline_24)
            .setContentTitle(title)
            .setContentTitle(contentTitle)
            .setStyle(NotificationCompat.BigTextStyle().bigText(bigText))
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        with(NotificationManagerCompat.from(context)) {
            notify(notificationId, builder.build())
        }
    }
}
