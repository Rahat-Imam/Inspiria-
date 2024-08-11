package com.motivation.inspiria.presentation.main_screen.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.motivation.inspiria.R
import com.motivation.inspiria.core.utils.getMotivationQuotesOfViewPager
import com.motivation.inspiria.ui.theme.AppMainColor
import com.motivation.inspiria.ui.theme.PoppinsFontMedium

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalViewPagerWithIndicators(onItemClicked : (Int) -> Unit) {
    val pagerState = rememberPagerState(initialPage = 1)
    var selectedPage by remember { mutableStateOf(1) }
    val listOfMotivationalQuotes = getMotivationQuotesOfViewPager()

    LaunchedEffect(Unit) {
        while (true) {
            kotlinx.coroutines.delay(3000)
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)

    ) {
        HorizontalPager(
            count = 5,
            state = pagerState,
            modifier = Modifier
                .height(320.dp)
                .fillMaxWidth(),
            itemSpacing = 2.dp,
            contentPadding = PaddingValues(start = 40.dp, end = 40.dp)
        ) { page ->
            selectedPage = page
            Card(
                shape = RoundedCornerShape(20.dp),
                modifier =
                Modifier
                    .padding(10.dp, 10.dp, 10.dp, 10.dp)
                    .height(
                        if (page != pagerState.currentPage) {
                            260.dp
                        } else {
                            360.dp
                        }
                    )
                    .clickable(indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = {
                            onItemClicked(pagerState.currentPage + 1)
                        }
                    )
            ){
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                )
                {
                    Image(
                        painter =
                        painterResource(id = R.drawable.view_pager_bg),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.fillMaxSize()
                    )
                    Text(
                        text = listOfMotivationalQuotes.get(index = page),
                        color = Color.White,
                        fontSize = 12.sp,
                        fontFamily = PoppinsFontMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(10.dp)
                    )
                }

            }

        }



        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp, 16.dp, 16.dp, 35.dp),
            activeColor = AppMainColor,
            inactiveColor = Color.LightGray,
            indicatorWidth = 10.dp,
            indicatorHeight = 10.dp,
            spacing = 8.dp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HorizontalViewPagerWithIndicatorsPreview(){
    HorizontalViewPagerWithIndicators{
    }
}
