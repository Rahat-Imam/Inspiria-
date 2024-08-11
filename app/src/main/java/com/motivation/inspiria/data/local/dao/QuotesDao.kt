package com.motivation.inspiria.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.motivation.inspiria.data.local.entities.QuotesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuotesDao {
    @Query("SELECT * FROM table_quotes ORDER BY id ASC")
    fun getQuotes(): Flow<List<QuotesEntity>>

    @Query("SELECT * FROM table_quotes WHERE category = :category")
    fun getQuotesByCategory(category:String): Flow<List<QuotesEntity>>

    @Query("SELECT * FROM table_quotes WHERE favorite == 1")
    fun getAllFavQuotes():Flow<List<QuotesEntity>>

    @Query("SELECT * FROM table_quotes WHERE author = :author")
    fun getQuotesByAuthor(author:String): Flow<List<QuotesEntity>>

    @Query("SELECT * FROM table_quotes WHERE id = :id")
    suspend fun getQuote(id: Int): QuotesEntity

    @Query("SELECT * FROM table_quotes WHERE date = :date AND month = :month")
    suspend fun getQuoteOfDay(date:Int, month:String): QuotesEntity

    @Query("UPDATE table_quotes SET favorite = :isFav WHERE id = :id")
    suspend fun setFavoriteQuote(isFav:Int, id:Int)

}