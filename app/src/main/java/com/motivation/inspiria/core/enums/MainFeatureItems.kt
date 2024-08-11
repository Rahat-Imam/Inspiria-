package com.motivation.inspiria.core.enums

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.motivation.inspiria.R

enum class MainFeatureItems(
    @DrawableRes val icon:Int,
    @StringRes val title:Int
) {

    QuoteOfDay(
//        R.drawable.icon_quote,
        R.drawable.feature_quote,
        R.string.quote_of_the_day,
    ),
    Author(
//        R.drawable.icon_author,
        R.drawable.feature_author_quote,
        R.string.author
    ),
    Motivation(
//        R.drawable.icon_motivation,
        R.drawable.feature_motivation,
        R.string.motivation
    ),
    Breathing(
//        R.drawable.icon_breathing,
        R.drawable.feature_breathing,
        R.string.breathing
    )

}