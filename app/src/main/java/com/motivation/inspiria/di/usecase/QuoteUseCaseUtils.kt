package com.motivation.inspiria.di.usecase

import com.motivation.inspiria.di.models.QuotesModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

fun Flow<List<QuotesModel>>.mapAuthor(authorName: String): Flow<List<QuotesModel>> {
    return transform { quotesList ->
        val filteredQuotes = quotesList.filter {
            it.author == authorName
        }
        emit(filteredQuotes)
    }
}


fun Flow<List<QuotesModel>>.mapCategory(categoryName: String): Flow<List<QuotesModel>> {
    return transform { quotesList ->
        val filteredQuotes = quotesList.filter {
            it.category == categoryName
        }
        emit(filteredQuotes)
    }
}

