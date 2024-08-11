package com.motivation.inspiria.presentation.quote_of_day_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.motivation.inspiria.ui.theme.PoppinsFontMedium


@Composable
fun QuoteAuthorText(quote: String, author:String) {
    Column{
        Text(
            text = "\"$quote\"",
            fontSize = 16.sp,
            fontFamily = PoppinsFontMedium,
            lineHeight = 30.sp,
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier
                .padding(30.dp, 5.dp, 30.dp, 5.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = author,
            fontSize = 14.sp,
            fontFamily = PoppinsFontMedium,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier
                .padding(30.dp)
                .align(Alignment.CenterHorizontally)
        )
    }

}

