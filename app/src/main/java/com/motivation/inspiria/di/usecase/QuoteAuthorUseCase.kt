package com.motivation.inspiria.di.usecase

import com.motivation.inspiria.data.repo.QuotesRepositoryImpl
import com.motivation.inspiria.di.models.QuotesModel
import kotlinx.coroutines.flow.Flow

class QuoteAuthorUseCase(val repo: QuotesRepositoryImpl) {

    operator fun invoke(): Flow<List<QuotesModel>> {
        return repo.getQuotesFromDb()
    }

    operator fun invoke(author: String): Flow<List<QuotesModel>> {
        return repo.getQuotesOfAuthor(author).mapAuthor(author)
    }
}