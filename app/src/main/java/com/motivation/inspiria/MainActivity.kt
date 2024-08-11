package com.motivation.inspiria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.motivation.inspiria.navigation.NavGraph
import com.motivation.inspiria.ui.theme.InspiriaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var darkThemeValue by rememberSaveable { mutableStateOf(false) }

            InspiriaTheme(
                darkTheme = darkThemeValue
            ){
                NavGraph(
                    navController = rememberNavController()
                ){
                    darkThemeValue =it
                }
            }
        }
    }
}
