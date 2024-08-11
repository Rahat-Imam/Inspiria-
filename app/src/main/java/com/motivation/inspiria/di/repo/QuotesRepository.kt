package com.motivation.inspiria.di.repo

import com.motivation.inspiria.di.models.QuotesModel
import kotlinx.coroutines.flow.Flow

interface QuotesRepository {
    fun getQuotesFromDb(): Flow<List<QuotesModel>>
    fun getQuotesByCategory(category:String): Flow<List<QuotesModel>>
    fun getAllFavQuotes(): Flow<List<QuotesModel>>
    fun getQuotesOfAuthor(author:String): Flow<List<QuotesModel>>
    suspend fun getQuoteFromDb(id:Int): QuotesModel
    suspend fun setFavoriteQuote(isFav:Int, id:Int)
    suspend fun getQuoteOfTheDay(date:Int, month:String): QuotesModel
}