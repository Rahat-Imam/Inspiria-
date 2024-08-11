package com.motivation.inspiria.presentation.main_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.motivation.inspiria.core.enums.MainFeatureItems
import com.motivation.inspiria.ui.theme.PoppinsFontMedium
import com.motivation.inspiria.ui.theme.PreviousBackColor


@Composable
fun MainAppFeatures(onItemSelected:(String)->Unit) {
    Box{
        LazyRow(
            modifier = Modifier.padding(10.dp),
            content = {
                items(MainFeatureItems.entries.size) { it->
                    GridItem(MainFeatureItems.entries[it]){
                        onItemSelected(it)
                    }
                }
            }
        )
    }
}

@Composable
fun GridItem(feature: MainFeatureItems, onItemSelected:(String)->Unit) {
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
                    text = stringResource(id = feature.title),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 10.sp,
                    fontFamily = PoppinsFontMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewMyGridView() {
//    MainAppFeatures()
//}
