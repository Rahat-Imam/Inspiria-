package com.motivation.inspiria.presentation.explore_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.motivation.inspiria.R
import com.motivation.inspiria.core.enums.ExploreItems
import com.motivation.inspiria.core.extension.copy
import com.motivation.inspiria.core.extension.share
import com.motivation.inspiria.core.presentation.DefaultTopAppBar
import com.motivation.inspiria.core.presentation.MainScreenWithBackground
import com.motivation.inspiria.presentation.explore_screen.components.QuoteInfo
import com.motivation.inspiria.presentation.explore_screen.components.QuoteView
import com.motivation.inspiria.presentation.explore_screen.viewmodel.ExploreViewModel
import com.motivation.inspiria.ui.theme.PoppinsFontRegular
import com.motivation.inspiria.ui.theme.PreviousBackColor
import org.koin.androidx.compose.inject

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ViewExploreScreen(exploreItemName:String, navController: NavController) {

    val context = LocalContext.current
    val viewModel: ExploreViewModel by inject()

    var selectedExploreItem by remember { mutableStateOf(ExploreItems.ExploreItem1)}

    LaunchedEffect(Unit) {
        for(item in ExploreItems.entries){
            if(item.name == exploreItemName){
                selectedExploreItem = item
            }
        }
    }

    MainScreenWithBackground(
        addScaffolding = true,
        topBar = {
            DefaultTopAppBar("Motivational Quote"){
                navController.popBackStack()
            }
        }
    ){ paddingValues->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){

            Column {

                AndroidView(
                    factory = { context ->
                        val quoteView = QuoteView(selectedExploreItem.quote, selectedExploreItem.background, ctx = context) { bitmap ->
                            viewModel.bitmapCreated(bitmap)
                        }
                        quoteView
                    })
                QuoteInfo(selectedExploreItem.quote, selectedExploreItem.background)


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(45.dp, 10.dp, 45.dp, 40.dp),
                ) {
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp)
                            .padding(5.dp)
                            .clickable(indication = null,
                                interactionSource = remember { MutableInteractionSource() },
                                onClick = {
                                    context.copy(selectedExploreItem.quote)
                                }
                            ),
                        elevation = CardDefaults.cardElevation(3.dp),
                        border = BorderStroke((0.4).dp, PreviousBackColor)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    MaterialTheme.colorScheme.onSecondary
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                modifier = Modifier.padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painterResource(id = R.drawable.icon_copy),
                                    contentDescription = null,
                                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                                )
                                Spacer(modifier = Modifier.size(5.dp))
                                Text(
                                    "Copy",
                                    fontSize = 12.sp,
                                    fontFamily = PoppinsFontRegular,
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp)
                            .padding(5.dp)
                            .clickable(indication = null,
                                interactionSource = remember { MutableInteractionSource() },
                                onClick = {
                                    context.share(selectedExploreItem.quote)
                                }
                            ),
                        elevation = CardDefaults.cardElevation(3.dp),
                        border = BorderStroke((0.4).dp, PreviousBackColor)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    MaterialTheme.colorScheme.onSecondary
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                modifier = Modifier.padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painterResource(
                                        id = R.drawable.icon_share
                                    ),
                                    contentDescription = null,
                                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                                )
                                Spacer(modifier = Modifier.size(5.dp))
                                Text(
                                    "Share",
                                    fontSize = 12.sp,
                                    fontFamily = PoppinsFontRegular,
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

