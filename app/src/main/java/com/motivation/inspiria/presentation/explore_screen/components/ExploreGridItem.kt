package com.motivation.inspiria.presentation.explore_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.motivation.inspiria.core.enums.ExploreItems
import com.motivation.inspiria.ui.theme.PoppinsFontMedium


@Composable
fun ExploreGridItem(exploreItems: ExploreItems, onItemSelected:(ExploreItems)->Unit) {

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        onClick = {
            onItemSelected(exploreItems)
        }
    ) {
        Box(
            contentAlignment = Alignment.Center
        ){
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(exploreItems.background),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )

            }
            Text(
                text = exploreItems.quote,
                fontSize = 10.sp,
                fontFamily = PoppinsFontMedium,
                lineHeight = 12.sp,
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier.padding(10.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }

    }

}
