package com.motivation.inspiria.presentation.main_screen.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.motivation.inspiria.core.enums.MainFeatureItems

@Composable
fun HomeScreen(navController: NavController, paddingValues : PaddingValues, navigateTo:()->Unit) {
    ContentMainScreen(navController ,paddingValues){
        when(it){
            MainFeatureItems.QuoteOfDay.name -> navController.navigate("QuoteOfTheDayScreen")
            MainFeatureItems.Motivation.name -> navController.navigate("MotivationScreen")
            MainFeatureItems.Author.name -> navController.navigate("AuthorScreen")
            MainFeatureItems.Breathing.name -> navController.navigate("BreathingScreen")
            "Categories" -> navigateTo()
            else -> "MainScreen"
        }
    }
}