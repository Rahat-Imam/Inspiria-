package com.motivation.inspiria.core

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.ForegroundInfo
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.motivation.inspiria.data.repo.QuotesRepositoryImpl
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


val Context.notificationManager: NotificationManager get() = getSystemService(NotificationManager::class.java)

class DataSyncWorkManager(private val context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters), KoinComponent {

    private var notificationManager: NotificationManager = context.notificationManager
    private var notificationBuilder: NotificationCompat.Builder? = null
    private val repository: QuotesRepositoryImpl by inject()

    override suspend fun getForegroundInfo(): ForegroundInfo =
        ForegroundInfo(NOTIFICATION_ID, foregroundNotification().build())


    override suspend fun doWork(): Result {

        repository.getQuotesFromDb()
        return Result.success()
    }


    private fun foregroundNotification(): NotificationCompat.Builder {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                DATA_SYNC_NOTIFICATION_CHANNEL,
                DATA_SYNC_NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        notificationBuilder = NotificationCompat.Builder(context, DATA_SYNC_NOTIFICATION_CHANNEL)
            .setContentTitle("Data sync")
            .setSmallIcon(android.R.drawable.ic_popup_sync)
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        return notificationBuilder!!
    }

    companion object {
        private const val NOTIFICATION_ID = 1002
        private const val DATA_SYNC_NOTIFICATION_CHANNEL = "data_sync_notification_channel"
        private const val DATA_SYNC_NOTIFICATION_CHANNEL_NAME = "data_sync_notification"
        private const val DATA_SYNC_WORKER_UUID = "data_sync_worker_uuid"

        fun startWorker(context: Context) {
            val worker = WorkManager.getInstance(context)
            worker.enqueueUniqueWork(
                DATA_SYNC_WORKER_UUID,
                ExistingWorkPolicy.KEEP, OneTimeWorkRequestBuilder<DataSyncWorkManager>()
//                    .setConstraints(constraints = Constraints())
//                    .setInputData(data.build())
                    .build()
            )
        }
    }
}
