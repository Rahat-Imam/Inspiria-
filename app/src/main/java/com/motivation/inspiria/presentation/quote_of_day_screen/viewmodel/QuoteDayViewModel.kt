package com.motivation.inspiria.presentation.quote_of_day_screen.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.motivation.inspiria.core.ConstantOfApp.DATE
import com.motivation.inspiria.core.ConstantOfApp.MONTH
import com.motivation.inspiria.di.models.DateMonth
import com.motivation.inspiria.di.models.QuotesModel
import com.motivation.inspiria.di.usecase.QuotesUseCase

class QuoteDayViewModel (private val quotesUseCase: QuotesUseCase) : ViewModel() {

    lateinit var savedStateHandle: SavedStateHandle

    fun setSavedStateHandler(date:Int, month:String) {
        savedStateHandle[DATE] = date
        savedStateHandle[MONTH] = month
    }

    private fun getSavedStateDateMonth(): DateMonth {
        val date = savedStateHandle.get<Int>(DATE) ?: 0
        val month = savedStateHandle.get<String>(MONTH) ?: ""
        return DateMonth(date, month)
    }

    suspend fun getQuoteOfDay(): QuotesModel {
        val (date, month) = getSavedStateDateMonth()
        return quotesUseCase.repo.getQuoteOfTheDay(date, month)
    }

}
