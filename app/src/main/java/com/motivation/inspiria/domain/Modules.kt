package com.motivation.inspiria.domain

import android.os.Build
import androidx.annotation.RequiresApi
import com.motivation.inspiria.core.QuotesDb
import com.motivation.inspiria.core.QuotesViewModel
import com.motivation.inspiria.core.utils.Preferences
import com.motivation.inspiria.core.utils.alarmManager.AlarmSchedulerImpl
import com.motivation.inspiria.data.local.entities.QuotesEntity
import com.motivation.inspiria.data.mappers.EntityModelMapper
import com.motivation.inspiria.data.mappers.QuotesEntityModelMapper
import com.motivation.inspiria.data.repo.QuotesRepositoryImpl
import com.motivation.inspiria.di.models.QuotesModel
import com.motivation.inspiria.di.usecase.QuoteAuthorUseCase
import com.motivation.inspiria.di.usecase.QuoteCategoriesUseCase
import com.motivation.inspiria.di.usecase.QuotesUseCase
import com.motivation.inspiria.presentation.author_screen.viewmodel.AuthorViewModel
import com.motivation.inspiria.presentation.categories_screen.viewmodel.CategoriesViewModel
import com.motivation.inspiria.presentation.explore_screen.viewmodel.ExploreViewModel
import com.motivation.inspiria.presentation.quote_of_day_screen.viewmodel.QuoteDayViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


@RequiresApi(Build.VERSION_CODES.O)
val appModules = module {
    single { QuotesDb.getDatabase(get()) }
    single { get<QuotesDb>().quotesDao() }
    single<EntityModelMapper<QuotesEntity, QuotesModel>> { QuotesEntityModelMapper() }
    single { QuotesRepositoryImpl(get(), get(), get()) }
    single { QuotesUseCase(get()) }
    single { QuoteAuthorUseCase(get()) }
    single { QuoteCategoriesUseCase(get()) }
    single { Preferences(get()) }
    single { AlarmSchedulerImpl(get()) }
    viewModel { QuotesViewModel(get(), get(), get()) }
    viewModel { AuthorViewModel(get()) }
    viewModel { CategoriesViewModel(get()) }
    viewModel { QuoteDayViewModel(get()) }
    viewModel { ExploreViewModel() }
}

