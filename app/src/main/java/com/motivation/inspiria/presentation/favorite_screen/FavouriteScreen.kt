package com.motivation.inspiria.presentation.favorite_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.motivation.inspiria.core.QuotesViewModel
import com.motivation.inspiria.core.presentation.Loading
import com.motivation.inspiria.core.presentation.ViewQuoteItems
import com.motivation.inspiria.ui.theme.PoppinsFontMedium
import com.motivation.inspiria.core.utils.UiStates
import org.koin.androidx.compose.inject

@Composable
fun FavouriteScreen(navController: NavController, paddingValues : PaddingValues) {

    val viewModel: QuotesViewModel by inject()

    val quotes by viewModel.getAllFavQuotes.collectAsStateWithLifecycle()

    when (quotes) {
        UiStates.Loading -> Loading(modifier = Modifier.fillMaxSize())
        is UiStates.Success -> {
            val list = (quotes as UiStates.Success).template

            if(list.size == 0){
                Box(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "No Fav Item Found!",
                        fontFamily = PoppinsFontMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                }
            }
            else{
                Box(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                ){
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 10.dp, 0.dp, 0.dp)
                    ) {
                        items(list.size) { item ->
                            ViewQuoteItems(list[item])
                        }
                    }

                }
            }

        }
    }
}

