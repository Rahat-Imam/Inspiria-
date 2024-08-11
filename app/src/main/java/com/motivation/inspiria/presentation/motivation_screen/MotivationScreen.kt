package com.motivation.inspiria.presentation.motivation_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.motivation.inspiria.R
import com.motivation.inspiria.core.enums.MotivationItems
import com.motivation.inspiria.core.presentation.DefaultTopAppBar
import com.motivation.inspiria.core.presentation.MainScreenWithBackground
import com.motivation.inspiria.presentation.motivation_screen.components.MotivationGridItem


@Composable
fun MotivationScreen(navController: NavController) {

    val context = LocalContext.current
    MainScreenWithBackground(
        addScaffolding = true,
        topBar = {

            DefaultTopAppBar(R.string.motivation) {
                navController.popBackStack()
            }
        }
    ){ paddingValues->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ){
            LazyColumn(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxSize(),
                content = {
                    items(MotivationItems.entries.size) {
                        MotivationGridItem(MotivationItems.entries[it], context){}
                    }
                }
            )
        }
    }

}
