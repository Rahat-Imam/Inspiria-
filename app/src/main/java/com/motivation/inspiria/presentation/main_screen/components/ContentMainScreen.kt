package com.motivation.inspiria.presentation.main_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.motivation.inspiria.core.enums.ExploreItems
import com.motivation.inspiria.core.extension.share
import com.motivation.inspiria.navigation.NavControllerRoutes
import com.motivation.inspiria.ui.theme.PoppinsFontMedium
import com.motivation.inspiria.ui.theme.PoppinsFontSemiBold

@Composable
fun ContentMainScreen(navController: NavController, paddingValues: PaddingValues, navigateTo:(String)->Unit) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingValues)
    ){
        Column {
            HorizontalViewPagerWithIndicators{
                val exploreItem = when(it){
                    1 -> ExploreItems.Motivation2.name
                    2 -> ExploreItems.Motivation1.name
                    3 -> ExploreItems.Motivation3.name
                    4 -> ExploreItems.Motivation4.name
                    5 -> ExploreItems.Motivation5.name
                    else -> ExploreItems.Motivation1.name
                }
                navController.navigate(
                    NavControllerRoutes.ViewExploreScreen(
                        exploreItem = exploreItem
                    ).getPathWithNavArgs()
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row{
                Text(
                    text = "Top Categories",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 13.sp,
                    fontFamily = PoppinsFontMedium,
                    lineHeight = 23.sp,
                    modifier = Modifier.padding(start = 10.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "View All",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 13.sp,
                    fontFamily = PoppinsFontMedium,
                    lineHeight = 23.sp,
                    modifier = Modifier.padding(end = 10.dp)
                        .clickable(indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = {
                               navigateTo("Categories")
                            }
                        )
                )
            }

            TopCategories{
                navController.navigate(
                    NavControllerRoutes.ViewQuotesScreen(
                        category = it
                    ).getPathWithNavArgs()
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row{
                Text(
                    text = "Main Features",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 13.sp,
                    fontFamily = PoppinsFontMedium,
                    lineHeight = 23.sp,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
            MainAppFeatures{
                navigateTo(it)
            }

            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContentMainScreenPreview(){
    ContentMainScreen(navController = rememberNavController(), paddingValues = PaddingValues(0.dp)) {

    }
}
