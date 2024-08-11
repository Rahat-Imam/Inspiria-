package com.motivation.inspiria.presentation.author_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.motivation.inspiria.R
import com.motivation.inspiria.core.enums.AuthorItems
import com.motivation.inspiria.core.presentation.DefaultTopAppBar
import com.motivation.inspiria.core.presentation.MainScreenWithBackground
import com.motivation.inspiria.navigation.NavControllerRoutes
import com.motivation.inspiria.presentation.author_screen.components.AuthorGridItem

@Composable
fun AuthorScreen(navController: NavController) {

    MainScreenWithBackground(
        addScaffolding = true,
        topBar = {

            DefaultTopAppBar(R.string.author) {
                navController.popBackStack()
            }
        }
    ){ paddingValues->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ){
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(5.dp),
                content = {
                    items(AuthorItems.entries.size) {
                        AuthorGridItem(AuthorItems.entries[it]){
                            navController.navigate(
                                NavControllerRoutes.ViewAuthorQuotesScreen(
                                    author = it.authorFullName
                                ).getPathWithNavArgs()
                            )
                        }
                    }
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AuthorScreenPreview(){
    AuthorScreen(rememberNavController())
}