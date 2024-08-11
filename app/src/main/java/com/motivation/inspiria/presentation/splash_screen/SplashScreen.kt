package com.motivation.inspiria.presentation.splash_screen

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.motivation.inspiria.R
import com.motivation.inspiria.core.utils.Preferences
import com.motivation.inspiria.navigation.NavControllerRoutes
import com.motivation.inspiria.ui.theme.PoppinsFontBold
import com.motivation.inspiria.ui.theme.PoppinsFontMedium
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import org.koin.androidx.compose.inject

@Composable
fun SplashScreen(navController:NavController, changeTheme:(Boolean)->Unit) {

    val prefs: Preferences by inject()
    val infiniteTransition = rememberInfiniteTransition(label = "IconRotationAnimInfinite")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 360F,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing)
        ), label = "IconRotationAnim"
    )

    LaunchedEffect(Unit) {
        changeTheme(prefs.getIsDarkTheme().first())
        delay(3000)
        navController.navigate(NavControllerRoutes.MainScreen().route)
    }

   Box(
       contentAlignment = Alignment.Center,
       modifier = Modifier
           .fillMaxSize()
           .background(MaterialTheme.colorScheme.background)
   ){
       Column(
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center,
       ) {
           Image(
               painter = painterResource(id = R.drawable.logo),
               contentDescription = "Motivation Logo",
               modifier = Modifier.graphicsLayer(rotationZ = angle)
           )
           Spacer(modifier = Modifier.height(10.dp))
           Text(
               fontFamily = PoppinsFontBold,
               text = stringResource(id = R.string.app_name_inspiria),
               fontWeight = FontWeight.Bold,
               color = MaterialTheme.colorScheme.primary,
               fontSize = 24.sp
           )
           Text(
               fontFamily = PoppinsFontMedium,
               color = MaterialTheme.colorScheme.primary,
               fontWeight = FontWeight.Medium,
               fontSize = 14.sp,
               text = stringResource(id = R.string.splash_description)
           )
           Spacer(modifier = Modifier.height(30.dp))
       }
   }
}
