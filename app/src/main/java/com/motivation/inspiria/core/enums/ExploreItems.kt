package com.motivation.inspiria.core.enums

import androidx.annotation.DrawableRes
import com.motivation.inspiria.R


enum class ExploreItems(
    @DrawableRes val background:Int,
    val quote:String,
    val author:String
) {

    ExploreItem1(
        R.drawable.bg_1,
        "Whatever you are, be a good one.",
        "Abraham Lincoln"
    ),

    ExploreItem2(
        R.drawable.bg_2,
        "A ship in harbor is safe, but that is not what ships are built for.",
        "John A. Shedd"
    ),

    ExploreItem3(
        R.drawable.bg_3,
        "Great things are done by a series of small things brought together.",
        "Vincent van Gogh"
    ),

    ExploreItem4(
        R.drawable.bg_4,
        "Fall seven times, stand up eight.",
        "Japanese proverb"
    ),

    ExploreItem5(
        R.drawable.bg_5,
        "The best revenge is massive success.",
        "Frank Sinatra"
    ),

    ExploreItem6(
        R.drawable.bg_6,
        "Keep your face always toward the sunshine — and shadows will fall behind you.",
        "Walt Whitman"
    ),

    ExploreItem7(
        R.drawable.bg_7,
        "In three words I can sum up everything I’ve learned about life: It goes on.",
        "Robert Frost"
    ),

    ExploreItem8(
        R.drawable.bg_8,
        "Life shrinks or expands in proportion to one’s courage.",
        "Anaïs Nin"
    ),

    ExploreItem9(
        R.drawable.bg_9,
        "In the middle of difficulty lies opportunity.",
        "Albert Einstein"
    ),

    ExploreItem10(
        R.drawable.bg_10,
        "The secret of getting ahead is getting started.",
        "Mark Twain"
    ),

    Motivation1(
        R.drawable.view_pager_bg,
        "Believe in yourself. Stay in your own lane. There’s only one you.",
        "Queen Latifah"
    ),

    Motivation2(
        R.drawable.view_pager_bg,
        "The harder the struggle, the more glorious the triumph. Self-realization demands very great struggle.",
        "Swami Sivananda"
    ),

    Motivation3(
        R.drawable.view_pager_bg,
        "You just can’t beat the person who never gives up.",
        "Babe Ruth"
    ),

    Motivation4(
        R.drawable.view_pager_bg,
        "In order to succeed. We must first believe that we can.",
        "Nikos Kazantzakis"
    ),

    Motivation5(
        R.drawable.view_pager_bg,
        "It is not the strength of the body that counts, but the strength of the spirit.",
        "J.R.R. Tolkien"
    ),
}