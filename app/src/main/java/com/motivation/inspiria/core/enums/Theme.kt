package com.motivation.inspiria.core.enums

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.motivation.inspiria.R

enum class Theme(
    @DrawableRes val icon:Int,
    val itemName:String,
    val background:Color,
    val textColor:Color
) {
    Light(
        R.drawable.icon_home_selected,
        "Theme",
        background = Color.White,
        textColor = Color.Black
    ),
    Dark(
        R.drawable.icon_home_selected,
        "Theme",
        background = Color.DarkGray,
        textColor = Color.White
    )
}