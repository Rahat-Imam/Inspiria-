package com.motivation.inspiria.core.presentation

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.motivation.inspiria.R
import com.motivation.inspiria.di.models.QuotesModel
import com.motivation.inspiria.core.QuotesViewModel
import com.motivation.inspiria.core.enums.Affirmations
import com.motivation.inspiria.core.enums.UserEvent
import com.motivation.inspiria.core.extension.copy
import com.motivation.inspiria.core.extension.share
import com.motivation.inspiria.ui.theme.PoppinsFontRegular
import com.motivation.inspiria.ui.theme.PreviousBackColor
import org.koin.androidx.compose.inject


@Composable
fun ViewQuoteItems(quotesMC: QuotesModel, showAuthorName:Boolean=true) {
    val viewModel: QuotesViewModel by inject()
    val context = LocalContext.current

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(20.dp, 5.dp, 20.dp, 5.dp)
            .clickable(indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = {}
            ),
        elevation = CardDefaults.cardElevation(3.dp),
        border = BorderStroke(width = (0.4).dp, PreviousBackColor)
    ) {
        Box(
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ){
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(10.dp, 15.dp, 10.dp, 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "\"${quotesMC.quote}\"",
                    fontSize = 13.sp,
                    fontFamily = PoppinsFontRegular,
                    lineHeight = 15.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier
                        .padding(30.dp, 5.dp, 30.dp, 5.dp)
                        .align(Alignment.CenterHorizontally)
                )
                if(showAuthorName){
                    Text(
                        text = quotesMC.author,
                        fontSize = 12.sp,
                        fontFamily = PoppinsFontRegular,
                        lineHeight = 12.sp,
                        maxLines = 1,
                        fontStyle = FontStyle.Italic,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(30.dp, 5.dp, 30.dp, 5.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp, start = 50.dp, end = 50.dp)
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.Bottom
            ) {

                Image(painterResource(
                    id = R.drawable.icon_copy),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .clickable(indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = {
                            context.copy(quotesMC.quote)
                        }
                    )

                )
                Image(
                    painterResource(
                        id = if(quotesMC.favorite==1)
                            R.drawable.icon_fav_selected
                        else
                            R.drawable.icon_fav_unselected
                    ),
                    contentDescription = null,
                    colorFilter =
                    if(quotesMC.favorite==0){
                        ColorFilter.tint(MaterialTheme.colorScheme.primary)
                    } else{ null },
                    modifier = Modifier
                        .clickable(indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = {
                                val isFav = if (quotesMC.favorite == 1) {
                                    0
                                } else {
                                    1
                                }
                                viewModel.onUserEvent(UserEvent.IsBookmark(isFav, quotesMC.id))
                            }
                        )
                )
                Image(painterResource(id = R.drawable.icon_share),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .clickable(indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = {
                            context.share(quotesMC.quote)

                        }
                    )
                )
            }

        }

    }
}
