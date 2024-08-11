package com.motivation.inspiria.core.enums

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.motivation.inspiria.R



enum class CategoryItems(
    @StringRes val category:Int,
    @DrawableRes val icon:Int
) {

    Inspiration(
        R.string.inspiration,
        R.drawable.icon_cat_inspiration
    ),

    Life(
        R.string.life,
        R.drawable.icon_cat_life
    ),

    Success(
        R.string.success,
        R.drawable.icon_cat_success
    ),

    Dreams(
        R.string.dreams,
        R.drawable.icon_cat_dreams
    ),

    Kindness(
        R.string.kindness,
        R.drawable.icon_cat_kindness
    ),

    Work(
        R.string.work,
        R.drawable.icon_cat_work
    ),

    Excellence(
        R.string.excellence,
        R.drawable.icon_cat_excellence
    ),

    Failure(
        R.string.failure,
        R.drawable.icon_cat_failure
    ),

    Pain(
        R.string.pain,
        R.drawable.icon_cat_pain
    ),

    Future(
        R.string.future,
        R.drawable.icon_cat_future
    )


}




enum class TopCategoryItems(
    @StringRes val category:Int,
    @DrawableRes val icon:Int
) {
    Success(
        R.string.success,
        R.drawable.icon_cat_success
    ),
    Dreams(
        R.string.dreams,
        R.drawable.icon_cat_dreams
    ),

    Inspiration(
        R.string.inspiration,
        R.drawable.icon_cat_inspiration
    ),

    Life(
        R.string.life,
        R.drawable.icon_cat_life
    )
}