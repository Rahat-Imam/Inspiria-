package com.motivation.inspiria.presentation.author_screen.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.motivation.inspiria.core.ConstantOfApp.AUTHOR_ID
import com.motivation.inspiria.di.models.QuotesModel
import com.motivation.inspiria.di.usecase.QuoteAuthorUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest

class AuthorViewModel(private val quoteAuthorUseCase: QuoteAuthorUseCase) : ViewModel() {

    lateinit var savedStateHandle: SavedStateHandle

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getQuotesOfAuthor(): Flow<List<QuotesModel>> {
        return savedStateHandle.getStateFlow(AUTHOR_ID, "")
            .flatMapLatest { author ->
                quoteAuthorUseCase(author)
            }
    }

    fun setSavedStateHandler(author: String) {
        savedStateHandle[AUTHOR_ID] = author
    }
}