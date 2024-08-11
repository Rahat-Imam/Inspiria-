package com.motivation.inspiria.presentation.categories_screen.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.motivation.inspiria.core.ConstantOfApp.CATEGORY_ID
import com.motivation.inspiria.di.models.QuotesModel
import com.motivation.inspiria.di.usecase.QuoteCategoriesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest

class CategoriesViewModel (private val quoteCategoriesUseCase: QuoteCategoriesUseCase) : ViewModel() {

    lateinit var savedStateHandle: SavedStateHandle

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getQuotesByCategory(): Flow<List<QuotesModel>> {
        return savedStateHandle.getStateFlow(CATEGORY_ID, "")
            .flatMapLatest { category ->
                quoteCategoriesUseCase(category)
            }
    }

    fun setSavedStateHandler(category: String) {
        savedStateHandle[CATEGORY_ID] = category
    }
}