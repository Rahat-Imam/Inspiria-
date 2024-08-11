package com.motivation.inspiria.data.repo

import android.content.Context
import com.motivation.inspiria.data.local.dao.QuotesDao
import com.motivation.inspiria.data.mappers.EntityModelMapper
import com.motivation.inspiria.data.local.entities.QuotesEntity
import com.motivation.inspiria.di.models.QuotesModel
import com.motivation.inspiria.di.repo.QuotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QuotesRepositoryImpl(
    private val quotesDao: QuotesDao,
    val context: Context,
    private val quoteEntityToModelMapper: EntityModelMapper<QuotesEntity, QuotesModel>
): QuotesRepository {

    override fun getQuotesFromDb(): Flow<List<QuotesModel>> {
        return quotesDao.getQuotes().map { entities ->
            entities.map { entity ->
                quoteEntityToModelMapper.entityToModelMapper(entity)
            }
        }
    }

    override fun getQuotesByCategory(category:String): Flow<List<QuotesModel>> {
        return quotesDao.getQuotesByCategory(category).map { entities->
            entities.map{ entity ->
                quoteEntityToModelMapper.entityToModelMapper(entity)
            }
        }
    }

    override fun getAllFavQuotes(): Flow<List<QuotesModel>> {
        return quotesDao.getAllFavQuotes().map { entities ->
            entities.map { entity ->
                quoteEntityToModelMapper.entityToModelMapper(entity)
            }
        }
    }

    override fun getQuotesOfAuthor(author:String): Flow<List<QuotesModel>> {
        return quotesDao.getQuotesByAuthor(author).map { entities->
            entities.map{ entity ->
                quoteEntityToModelMapper.entityToModelMapper(entity)
            }
        }
    }

    override suspend fun getQuoteOfTheDay(date: Int, month: String) : QuotesModel {
        val entity: QuotesEntity = quotesDao.getQuoteOfDay(date, month)
        return entity.let{ quoteEntityToModelMapper.entityToModelMapper(it)}
    }

    override suspend fun getQuoteFromDb(id: Int): QuotesModel {
        val entity: QuotesEntity = quotesDao.getQuote(id)
        return entity.let { quoteEntityToModelMapper.entityToModelMapper(it) }
    }

    override suspend fun setFavoriteQuote(isFav: Int, id: Int) {
        quotesDao.setFavoriteQuote(isFav, id)
    }
}