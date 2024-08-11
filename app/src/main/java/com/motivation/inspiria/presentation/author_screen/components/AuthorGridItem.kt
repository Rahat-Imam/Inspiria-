package com.motivation.inspiria.presentation.author_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.motivation.inspiria.R
import com.motivation.inspiria.core.enums.AuthorItems
import com.motivation.inspiria.ui.theme.PoppinsFontMedium


@Composable
fun AuthorGridItem(author: AuthorItems, onItemSelected:(AuthorItems)->Unit) {

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable(indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = {
                    onItemSelected(author)
                }
            ),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {

        Column(
            Modifier
                .background(MaterialTheme.colorScheme.onSecondary)
                .fillMaxSize()
                .padding(top = 20.dp, bottom = 15.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

                Image(
                    painter = painterResource(id = author.authorImage),
                    contentDescription = null,
                    Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                )


            Text(
                text = stringResource(id = author.authorName),
                fontSize = 13.sp,
                fontFamily = PoppinsFontMedium,
                lineHeight = 12.sp,
                textAlign = TextAlign.Justify,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .align(Alignment.CenterHorizontally),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,

                )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun AuthorGridItemPreview(){
    AuthorGridItem(AuthorItems.AuthorHelenKeller){}
}