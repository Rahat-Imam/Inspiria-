package com.motivation.inspiria.di.usecase

import com.motivation.inspiria.di.models.QuotesModel
import com.motivation.inspiria.data.repo.QuotesRepositoryImpl
import kotlinx.coroutines.flow.Flow

class QuotesUseCase(val repo: QuotesRepositoryImpl) {

    operator fun invoke(): Flow<List<QuotesModel>> {
        return repo.getQuotesFromDb()
    }

}