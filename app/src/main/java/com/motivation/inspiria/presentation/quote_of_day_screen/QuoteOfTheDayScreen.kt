package com.motivation.inspiria.presentation.quote_of_day_screen

import android.annotation.SuppressLint
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
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import com.motivation.inspiria.core.QuotesViewModel
import com.motivation.inspiria.R
import com.motivation.inspiria.core.enums.UserEvent
import com.motivation.inspiria.core.extension.copy
import com.motivation.inspiria.core.extension.share
import com.motivation.inspiria.core.presentation.DefaultTopAppBar
import com.motivation.inspiria.core.presentation.MainScreenWithBackground
import com.motivation.inspiria.core.receivers.DateTimeChangeListener
import com.motivation.inspiria.core.utils.getCurrentTime
import com.motivation.inspiria.di.models.QuotesModel
import com.motivation.inspiria.presentation.quote_of_day_screen.components.QuoteAuthorText
import com.motivation.inspiria.presentation.quote_of_day_screen.viewmodel.QuoteDayViewModel
import com.motivation.inspiria.ui.theme.BackgroundColor
import com.motivation.inspiria.ui.theme.PoppinsFontRegular
import org.koin.androidx.compose.inject

@SuppressLint("SimpleDateFormat")
@Composable
fun QuoteOfTheDayScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel: QuotesViewModel by inject()
    val mainViewModel: QuoteDayViewModel by inject()
    mainViewModel.savedStateHandle = SavedStateHandle()
    var quoteState by remember { mutableStateOf(QuotesModel()) }
    var isFav by remember { mutableIntStateOf(0) }
    var currentTime by remember { mutableStateOf(getCurrentTime()) }

    DateTimeChangeListener {
        currentTime = getCurrentTime()
    }

    LaunchedEffect(currentTime, isFav) {
        mainViewModel.setSavedStateHandler(currentTime.first, currentTime.second)
        quoteState = mainViewModel.getQuoteOfDay()
    }


    MainScreenWithBackground(
        addScaffolding = true,
        topBar = {
            DefaultTopAppBar(R.string.quote_of_the_day) {
                navController.popBackStack()
            }
        }
    ) { paddingValues ->

        isFav = quoteState.favorite

        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .paint(
                    painterResource(id = R.drawable.quote_of_day_bg),
                    contentScale = ContentScale.FillBounds
                )
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Spacer(modifier = Modifier.size(100.dp))
                QuoteAuthorText(quote = quoteState.quote, author = quoteState.author)
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier.fillMaxWidth().height(200.dp) ,
                    contentAlignment = Alignment.Center
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 14.dp, end = 20.dp)
                            .align(Alignment.BottomEnd),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.End
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.icon_copy),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(color = Color.White),
                            modifier = Modifier
                                .size(30.dp)
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() },
                                    onClick = {
                                        context.copy(quoteState.quote)
                                    }
                                )
                        )

                        Image(
                            painter = painterResource(
                                id = if (quoteState.favorite == 1)
                                    R.drawable.icon_fav_selected
                                else
                                    R.drawable.icon_fav_unselected
                            ),
                            contentDescription = null,
                            colorFilter = if (quoteState.favorite == 0) {
                                ColorFilter.tint(Color.White)
                            } else {
                                null
                            },
                            modifier = Modifier
                                .size(35.dp)
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() },
                                    onClick = {
                                        isFav = if (quoteState.favorite == 1) {
                                            0
                                        } else {
                                            1
                                        }
                                        viewModel.onUserEvent(UserEvent.IsBookmark(isFav, quoteState.id))
                                    }
                                )
                        )

                        Image(
                            painter = painterResource(id = R.drawable.icon_share),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(color = Color.White),
                            modifier = Modifier
                                .size(30.dp)
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() },
                                    onClick = {
                                        context.share(quoteState.quote)
                                    }
                                )
                        )
                    }


                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}
