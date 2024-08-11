package com.motivation.inspiria.di.usecase

import com.motivation.inspiria.data.repo.QuotesRepositoryImpl
import com.motivation.inspiria.di.models.QuotesModel
import kotlinx.coroutines.flow.Flow

class QuoteCategoriesUseCase(val repo: QuotesRepositoryImpl) {

    operator fun invoke(): Flow<List<QuotesModel>> {
        return repo.getQuotesFromDb()
    }

    operator fun invoke(category: String): Flow<List<QuotesModel>> {
        return repo.getQuotesByCategory(category).mapCategory(category)
    }
}