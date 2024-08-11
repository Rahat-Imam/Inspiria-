package com.motivation.inspiria.core.enums

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.motivation.inspiria.R

enum class AuthorItems(
    @StringRes val authorName:Int,
    @DrawableRes val authorImage:Int,
    val authorFullName:String
) {

    AuthorTonyRobbins(
        R.string.author_1,
        R.drawable.author_tony_robbins,
        "Tony Robbins"
    ),

    AuthorZigZiglar(
        R.string.author_2,
        R.drawable.author_zig_ziglar,
        "Zig Ziglar"
    ),

    AuthorNapoleonHill(
        R.string.author_3,
        R.drawable.author_napoleon_hill,
        "Napoleon Hill"
    ),

    AuthorJimRohn(
        R.string.author_4,
        R.drawable.author_jim_rohn,
        "Jim Rohn"
    ),

    AuthorNormanVincentPeale(
        R.string.author_5_short,
        R.drawable.author_norman_v_peale,
        "Norman Vincent Peale"
    ),

    AuthorDaleCarnegie(
        R.string.author_6,
        R.drawable.author_dale_carnegie,
        "Dale Carnegie"
    ),

    AuthorLesBrown(
        R.string.author_7,
        R.drawable.author_les_brown,
        "Les Brown"
    ),

    AuthorWayneDyer(
        R.string.author_8,
        R.drawable.author_wayne_dyer,
        "Wayne Dyer"
    ),

    AuthorBrianTracy(
        R.string.author_9,
        R.drawable.author_brian_tracy,
        "Brian Tracy"
    ),

    AuthorHelenKeller(
        R.string.author_10,
        R.drawable.author_helen_keller,
        "Helen Keller"
    )

}