package com.motivation.inspiria.presentation.main_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.motivation.inspiria.core.enums.CategoryItems
import com.motivation.inspiria.core.enums.MainFeatureItems
import com.motivation.inspiria.core.enums.TopCategoryItems
import com.motivation.inspiria.ui.theme.PoppinsFontMedium
import com.motivation.inspiria.ui.theme.PreviousBackColor

@Composable
fun TopCategories(onItemSelected:(String)->Unit) {

    Box{
        LazyRow(
            modifier = Modifier.padding(10.dp),
            content = {
                items(TopCategoryItems.entries.size) { it->
                    GridItem(TopCategoryItems.entries[it]){
                        onItemSelected(it)
                    }
                }
            }
        )
    }
}

@Composable
fun GridItem(feature: TopCategoryItems, onItemSelected:(String)->Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .width(100.dp)
            .padding(end = 10.dp)
        ,
        elevation = CardDefaults.cardElevation(2.dp),
        border = BorderStroke((0.3).dp, PreviousBackColor),
        onClick = {
            onItemSelected(feature.name)
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onSecondary),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(feature.icon),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .padding(4.dp)
                        .size(40.dp)
                )
                Text(
                    text = stringResource(id = feature.category),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 10.sp,
                    fontFamily = PoppinsFontMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}