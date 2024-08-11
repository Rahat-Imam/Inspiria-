package com.motivation.inspiria.presentation.affirmations_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.motivation.inspiria.R
import com.motivation.inspiria.core.enums.Affirmations
import com.motivation.inspiria.core.extension.copy
import com.motivation.inspiria.core.extension.share
import com.motivation.inspiria.ui.theme.PoppinsFontMedium
import com.motivation.inspiria.ui.theme.PoppinsFontRegular
import com.motivation.inspiria.ui.theme.PreviousBackColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ViewPagerAffirmations() {

    val pagerState = rememberPagerState(initialPage = 2)
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(550.dp)
    ) {
        Column{

            HorizontalPager(
                count = Affirmations.entries.size,
                state = pagerState,
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f),
                itemSpacing = 1.dp,
                contentPadding = PaddingValues(start = 40.dp, end = 40.dp)
            ) { page ->

                Card(
                    shape = RoundedCornerShape(20.dp),
                    modifier =
                    Modifier
                        .padding(10.dp, 10.dp, 10.dp, 10.dp)
                        .clickable(indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = {

                            }
                        )
                        .height(
                            if (page != pagerState.currentPage) {
                                260.dp
                            } else {
                                500.dp
                            }
                        )
                ){
                    Box{
                        Image(
                            painter =
                            painterResource(id = Affirmations.entries[page].background),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier.fillMaxSize()
                        )
                        Text(
                            text = "\"${Affirmations.entries[page].description}\"",
                            color = Color.White,
                            fontSize = 13.sp,
                            fontFamily = PoppinsFontMedium,
                            lineHeight = 23.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(10.dp)
                        )


                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ){
                Card(
                    shape = CircleShape,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(5.dp)
                        .clickable(indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = {
                                if (pagerState.currentPage != 0) {
                                    coroutineScope.launch {
                                        val nextPage =
                                            (pagerState.currentPage - 1) % pagerState.pageCount
                                        pagerState.animateScrollToPage(nextPage)
                                    }
                                }

                            }
                        )
                ) {
                    Box (modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.backward),
                            contentDescription = "Backward",
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(8.dp),
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer)
                        )}
                }

                Card(
                    shape = CircleShape,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(5.dp)
                        .clickable(indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = {
                                if (pagerState.currentPage != Affirmations.entries.size) {
                                    coroutineScope.launch {
                                        val nextPage =
                                            (pagerState.currentPage + 1) % pagerState.pageCount
                                        pagerState.animateScrollToPage(nextPage)
                                    }
                                }
                            }
                        )

                ) {
                    Box (modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)){
                        Image(
                            painter = painterResource(id = R.drawable.forward),
                            contentDescription = "Forward",
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(8.dp),
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer)
                        )}
                }
            }


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
                                context.copy(Affirmations.entries[pagerState.currentPage].description)
                            }
                        ),
                    elevation = CardDefaults.cardElevation(3.dp),
                    border = BorderStroke((0.4).dp, PreviousBackColor)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.onSecondary),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(painterResource(
                                id = R.drawable.icon_copy),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                            )
                            Spacer(modifier = Modifier.size(5.dp))
                            Text(
                                "Copy",
                                fontSize = 12.sp,
                                fontFamily = PoppinsFontRegular,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
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
                                context.share(Affirmations.entries[pagerState.currentPage].description)
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
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                }
            }
        }



    }

}

@Preview(showBackground = true)
@Composable
fun PreviewiewPager(){
    ViewPagerAffirmations()
}