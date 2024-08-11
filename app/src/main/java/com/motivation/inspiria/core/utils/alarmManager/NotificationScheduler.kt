package  com.motivation.inspiria.core.utils.alarmManager

import org.threeten.bp.LocalDateTime

interface NotificationScheduler {
    fun scheduleAlarm(localDateTime: LocalDateTime, message: String, action: String,alarmId: Int)
    fun scheduleAlarm(time: Long, message: String, action: String,alarmId: Int)
    fun isAlarmAlreadySchedule(alarmId: Int,action: String): Boolean
    fun cancelAlarmById(alarmId: Int)
}