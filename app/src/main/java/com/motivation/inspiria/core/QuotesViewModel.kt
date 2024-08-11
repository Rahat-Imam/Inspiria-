package com.motivation.inspiria.core

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.motivation.inspiria.core.ConstantOfApp.ACTION_DAILY_MOTIVATION_REMINDER
import com.motivation.inspiria.core.ConstantOfApp.dailyMotivationReminderId
import com.motivation.inspiria.core.enums.UserEvent
import com.motivation.inspiria.core.utils.Preferences
import com.motivation.inspiria.core.utils.UiStates
import com.motivation.inspiria.core.utils.alarmManager.AlarmSchedulerImpl
import com.motivation.inspiria.core.utils.getCurrentTime
import com.motivation.inspiria.core.utils.nextDayMorning
import com.motivation.inspiria.core.utils.nextDayNoon
import com.motivation.inspiria.di.usecase.QuotesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDateTime
import java.util.Calendar

class QuotesViewModel(
    private val quotesUseCase: QuotesUseCase,
    private val notificationSchedulerImpl: AlarmSchedulerImpl,
    private val prefs: Preferences
) : ViewModel() {

    init{
        scheduleNotification()
    }

    private fun scheduleNotification(){
        if(!notificationSchedulerImpl.isAlarmAlreadySchedule(dailyMotivationReminderId,ACTION_DAILY_MOTIVATION_REMINDER)){
            viewModelScope.launch {
                if(prefs.getIsNotificationEnabled().first()){
                    val current = getCurrentTime()
                    val model = quotesUseCase.repo.getQuoteOfTheDay(current.first, current.second)

                    notificationSchedulerImpl.scheduleAlarm(
                        LocalDateTime.now().nextDayMorning(), model.quote,
                        action = ACTION_DAILY_MOTIVATION_REMINDER,
                        alarmId = dailyMotivationReminderId
                    )

                    Toast.makeText(quotesUseCase.repo.context, "Notification Scheduled", Toast.LENGTH_SHORT).show()

                }
            }
        }

    }

    fun scheduleNotification(hour:Int, minute:Int){


        viewModelScope.launch {
            if (prefs.getIsNotificationEnabled().first()) {
                val current = getCurrentTime()
                val model = quotesUseCase.repo.getQuoteOfTheDay(current.first, current.second)

                val calendar = Calendar.getInstance()
                calendar.timeInMillis = System.currentTimeMillis()
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, minute)

                notificationSchedulerImpl.scheduleAlarm(
                    calendar.timeInMillis, model.quote,
                    action = ACTION_DAILY_MOTIVATION_REMINDER,
                    alarmId = dailyMotivationReminderId
                )
                Toast.makeText(
                    quotesUseCase.repo.context,
                    "Notification Scheduled",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
    private val _title: MutableStateFlow<String> = MutableStateFlow("Quotes")
    val title = _title.asStateFlow()


    val getAllFavQuotes = quotesUseCase.repo.getAllFavQuotes()
        .map {
            UiStates.Success(it)
        }
        .flowOn(Dispatchers.IO)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiStates.Loading
        )

    fun onUserEvent(event : UserEvent){
        when(event){
            is UserEvent.IsBookmark -> bookmark(event)
        }
    }

    private fun bookmark(event: UserEvent.IsBookmark){
        viewModelScope.launch {
            quotesUseCase.repo.setFavoriteQuote(event.bookMarkValue, event.id)
        }
    }

}
