package com.motivation.inspiria.core.enums

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.motivation.inspiria.R

enum class BottomNavItems(
    @DrawableRes val selectedIcon:Int,
    @DrawableRes val unSelectedIcon:Int,
    @StringRes val navName:Int
) {
    Home(
        R.drawable.icon_home_selected,
        R.drawable.icon_home_unselected,
        R.string.home
    ),
    Explore(
        R.drawable.icon_explore_selected,
        R.drawable.icon_explore_unselected,
        R.string.explore
    ),
    Categories(
        R.drawable.icon_categories_selected,
        R.drawable.icon_categories_unselected,
        R.string.categories
    ),
    Favorites(
        R.drawable.icon_favorite_selected,
        R.drawable.icon_favorite_unselected,
        R.string.favorites
    )
}