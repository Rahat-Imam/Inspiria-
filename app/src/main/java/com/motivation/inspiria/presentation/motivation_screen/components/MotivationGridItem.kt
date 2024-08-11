package com.motivation.inspiria.presentation.motivation_screen.components

import android.content.Context
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.motivation.inspiria.R
import com.motivation.inspiria.core.enums.MotivationItems
import com.motivation.inspiria.core.enums.Theme
import com.motivation.inspiria.core.extension.read
import com.motivation.inspiria.core.extension.share
import com.motivation.inspiria.core.utils.Preferences
import com.motivation.inspiria.ui.theme.PoppinsFontMedium
import com.motivation.inspiria.ui.theme.PoppinsFontRegular
import com.motivation.inspiria.ui.theme.PoppinsFontSemiBold
import kotlinx.coroutines.flow.first
import org.koin.androidx.compose.inject


@Composable
fun MotivationGridItem(motivationItem: MotivationItems, context: Context, onItemSelected: (MotivationItems) -> Unit) {

    val prefs: Preferences by inject()
    var themeValue by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = Theme.Light) {
        themeValue = prefs.getIsDarkTheme().first()
    }

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable(indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = {
                    onItemSelected(motivationItem)
                }
            ),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {

        Column(
            Modifier
                .background(MaterialTheme.colorScheme.onSecondary)
                .fillMaxSize()
        ) {
            Row{
                Image(

                    painter = painterResource(
                        id =
                        if(themeValue){
                            motivationItem.darkIcon
                        }
                        else {
                            motivationItem.lightIcon
                        }),

                    contentDescription = null,
                    modifier = Modifier.padding(10.dp)
                )
                Column(
                    Modifier.fillMaxWidth()
                ){
                    Text(
                        text = "Article",
                        fontSize = 14.sp,
                        fontFamily = PoppinsFontSemiBold,
                        lineHeight = 12.sp,
                        textAlign = TextAlign.Justify,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier
                            .padding(10.dp, 10.dp, 10.dp, 0.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp, 0.dp, 10.dp, 0.dp)
                    ){
                        Image(
                            painterResource(
                            id = R.drawable.icon_bullet),
                            contentDescription = null,
                            Modifier.padding(0.dp,5.dp,0.dp,0.dp),
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = motivationItem.point1,
                            fontSize = 12.sp,
                            fontFamily = PoppinsFontMedium,
                            lineHeight = 14.sp,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }

                    if(motivationItem.point2!=null){
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp, 0.dp, 10.dp, 0.dp),
                        ) {
                            Image(
                                painterResource(id = R.drawable.icon_bullet),
                                contentDescription = null,
                                Modifier.padding(0.dp,5.dp,0.dp,0.dp),
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer),
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = motivationItem.point2,
                                fontSize = 12.sp,
                                fontFamily = PoppinsFontMedium,
                                lineHeight = 14.sp,
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                            )
                        }
                    }
                    if(motivationItem.point3!=null){
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp, 0.dp, 10.dp, 0.dp),
                        ) {
                            Image(
                                painterResource(id = R.drawable.icon_bullet),
                                contentDescription = null,
                                Modifier.padding(0.dp,5.dp,0.dp,0.dp),
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer),
                                )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = motivationItem.point3,
                                fontSize = 12.sp,
                                fontFamily = PoppinsFontMedium,
                                lineHeight = 14.sp,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }
                    }
                    if(motivationItem.point4!=null){
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp, 0.dp, 10.dp, 0.dp),
                        )  {
                            Image(
                                painter = painterResource(id = R.drawable.icon_bullet),
                                contentDescription = null,
                                Modifier.padding(0.dp,5.dp,0.dp,0.dp),
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer),
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = motivationItem.point4,
                                fontSize = 12.sp,
                                fontFamily = PoppinsFontMedium,
                                lineHeight = 14.sp,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(30.dp, 10.dp, 30.dp,10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp)
                        .padding(5.dp)
                        .clickable(indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = {
                                read(motivationItem.link, context)
                            }
                        ),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(painterResource(id = R.drawable.icon_article_ic), contentDescription = null,
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                            )
                            Spacer(modifier = Modifier.size(5.dp))
                            androidx.compose.material.Text(
                                "Read",
                                fontSize = 12.sp,
                                fontFamily = PoppinsFontRegular,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }
                    }
                }

                Card(
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp)
                        .padding(5.dp)
                        .clickable(indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = {
                                context.share("Checkout this motivational article: "+motivationItem.link)
                            }
                        )
                    ,
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(painterResource(id = R.drawable.icon_share), contentDescription = null,
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                            )
                            Spacer(modifier = Modifier.size(5.dp))
                            androidx.compose.material.Text(
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
