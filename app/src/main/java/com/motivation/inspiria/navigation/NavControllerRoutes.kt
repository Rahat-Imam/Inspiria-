package com.motivation.inspiria.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.motivation.inspiria.core.ConstantOfApp.AUTHOR_NAME
import com.motivation.inspiria.core.ConstantOfApp.CATEGORY_NAME
import com.motivation.inspiria.core.ConstantOfApp.EXPLORE_ITEM

sealed class NavControllerRoutes(val route:String) {

    data class ROOT(val rout:String = "root") : NavControllerRoutes(rout)
    data class MainScreen(val rout:String = "MainScreen") : NavControllerRoutes(rout)
    data class AuthorScreen(val rout:String = "AuthorScreen") : NavControllerRoutes(rout)
    data class BreathingScreen(val rout:String = "BreathingScreen") : NavControllerRoutes(rout)
    data class QuoteOfTheDayScreen(val rout:String = "QuoteOfTheDayScreen") : NavControllerRoutes(rout)
    data class MotivationScreen(val rout:String = "MotivationScreen") : NavControllerRoutes(rout)
    data class AffirmationsScreen(val rout:String = "AffirmationsScreen") : NavControllerRoutes(rout)
    data class ScheduleNotificationsScreen(val rout:String = "ScheduleNotificationsScreen") : NavControllerRoutes(rout)

    data class ViewExploreScreen(
        val rout: String = "ViewExploreScreen",
        val exploreItem: String = "") : NavControllerRoutes(rout)
    {
        val listOfArguments = listOf(
            navArgument(EXPLORE_ITEM) {
                type = NavType.StringType
                defaultValue = exploreItem
            }
        )
        fun getPath() = "$route/{$EXPLORE_ITEM}"
        fun getPathWithNavArgs(): String {
            var mainPath = rout
            if (exploreItem != "")
                mainPath += "/$exploreItem"
            return mainPath
        }
    }


    data class ViewQuotesScreen(
        val rout:String = "ViewQuotesScreen",
        val category:String = ""): NavControllerRoutes(rout){

        val listOfArguments = listOf(
            navArgument(CATEGORY_NAME) {
                type = NavType.StringType
                defaultValue = category
            }
        )
        fun getPath() = "$route/{$CATEGORY_NAME}"
        fun getPathWithNavArgs(): String {
            var mainPath = rout
            if (category != "")
                mainPath += "/$category"
            return mainPath
        }

    }


    data class ViewAuthorQuotesScreen(
        val rout: String = "ViewAuthorQuotesScreen",
        val author:String = ""
    ): NavControllerRoutes(rout){

        val listOfArguments = listOf(
            navArgument(AUTHOR_NAME) {
                type = NavType.StringType
                defaultValue = author
            }
        )
        fun getPath() = "$route/{$AUTHOR_NAME}"
        fun getPathWithNavArgs(): String {
            var mainPath = rout
            if (author != "")
                mainPath += "/$author"
            return mainPath
        }

    }

}