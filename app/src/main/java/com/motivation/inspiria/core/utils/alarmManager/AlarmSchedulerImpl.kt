package  com.motivation.inspiria.core.utils.alarmManager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.motivation.inspiria.core.receivers.AlarmReceiver
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId

class AlarmSchedulerImpl(private val context: Context) : NotificationScheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    override fun scheduleAlarm(
        localDateTime: LocalDateTime,
        message: String,
        action: String,
        alarmId: Int
    ) {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            this.action = action
            putExtra("message", message)
        }

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            localDateTime.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000,
            PendingIntent.getBroadcast(
                context,
                alarmId,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

    override fun scheduleAlarm(
        time: Long,
        message: String,
        action: String,
        alarmId: Int
    ) {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            this.action = action
            putExtra("message", message)
        }

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            PendingIntent.getBroadcast(
                context,
                alarmId,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }


    override fun isAlarmAlreadySchedule(alarmId: Int, action: String): Boolean {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            this.action = action
        }
        return PendingIntent.getBroadcast(
            context, alarmId, intent,
            PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE
        ) != null
    }

    override fun cancelAlarmById(alarmId: Int) {
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                alarmId,
                Intent(context, AlarmReceiver::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

}