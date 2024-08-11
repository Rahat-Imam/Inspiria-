package com.motivation.inspiria.presentation.categories_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.motivation.inspiria.core.presentation.DefaultTopAppBar
import com.motivation.inspiria.core.presentation.MainScreenWithBackground
import com.motivation.inspiria.core.presentation.ViewQuoteItems
import com.motivation.inspiria.presentation.categories_screen.viewmodel.CategoriesViewModel
import org.koin.androidx.compose.inject

@Composable
fun ViewQuotesScreen(categoryName: String, navigateBack: () -> Boolean) {

    val viewModel: CategoriesViewModel by inject()
    viewModel.savedStateHandle = SavedStateHandle()
    viewModel.setSavedStateHandler(categoryName)
    val quotes by viewModel.getQuotesByCategory().collectAsStateWithLifecycle(emptyList())

    MainScreenWithBackground(
        addScaffolding = true,
        topBar = {
            DefaultTopAppBar(categoryName){
                navigateBack()
            }
        }
    ){ paddingValues->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp, 0.dp, 0.dp)
            ) {
                items(quotes.size) { item ->
                    ViewQuoteItems(quotes[item])
                }
            }

        }


    }

}

