package com.motivation.inspiria.presentation.explore_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.motivation.inspiria.core.enums.ExploreItems
import com.motivation.inspiria.navigation.NavControllerRoutes
import com.motivation.inspiria.presentation.explore_screen.components.ExploreGridItem

@Composable
fun ExploreScreen(navController: NavController, paddingValues : PaddingValues) {

    Box(
        modifier = Modifier.padding(paddingValues)
    ){
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(5.dp),
            content = {
                items(10) { it->
                    ExploreGridItem(ExploreItems.entries[it]){
                        navController.navigate(
                            NavControllerRoutes.ViewExploreScreen(
                                exploreItem = it.name
                            ).getPathWithNavArgs()
                        )
                    }
                }
            }
        )
    }
}
