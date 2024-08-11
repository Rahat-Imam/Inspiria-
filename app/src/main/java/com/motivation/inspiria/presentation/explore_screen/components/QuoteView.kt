package com.motivation.inspiria.presentation.explore_screen.components

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.motivation.inspiria.R
import com.motivation.inspiria.ui.theme.PoppinsFontMedium
import com.motivation.inspiria.core.utils.GraphicUtils


@SuppressLint("ViewConstructor")
@ExperimentalMaterialApi
class QuoteView(quote:String, background:Int, ctx: Context, onBitmapCreated: (bitmap: Bitmap) -> Unit) : LinearLayoutCompat(ctx) {

    init {
        val width = 600
        val height = 670

        val view = ComposeView(ctx)
        view.visibility = View.GONE
        view.layoutParams = ViewGroup.LayoutParams(width, height)
        this.addView(view)

        view.setContent {
            QuoteInfo(quote, background)
        }

        viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val graphicUtils = GraphicUtils()
                val bitmap = graphicUtils.createBitmapFromView(view = view, width = width, height = height)
                onBitmapCreated(bitmap)
                viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }
}

@Composable
fun QuoteInfo(quote:String, background:Int) {

    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.padding(10.dp)
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
        )
        {
            Image(
                painter =
                painterResource(id = background),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = quote,
                color = Color.White,
                fontSize = 15.sp,
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