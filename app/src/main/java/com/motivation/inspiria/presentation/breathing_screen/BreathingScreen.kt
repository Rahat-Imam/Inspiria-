package com.motivation.inspiria.presentation.breathing_screen

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.motivation.inspiria.R
import com.motivation.inspiria.core.enums.Theme
import com.motivation.inspiria.core.presentation.DefaultTopAppBar
import com.motivation.inspiria.core.presentation.MainScreenWithBackground
import com.motivation.inspiria.core.utils.Preferences
import com.motivation.inspiria.ui.theme.AppMainColor
import com.motivation.inspiria.ui.theme.AppMainColorTransparent
import com.motivation.inspiria.ui.theme.PoppinsFontMedium
import com.motivation.inspiria.ui.theme.PoppinsFontRegular
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.androidx.compose.inject

@Composable
fun BreathingScreen(navController: NavController) {

    var isAnimating by remember { mutableStateOf(false) }
    var breathingText by remember { mutableStateOf("Inhale") }
    var cycleCount by remember { mutableStateOf(0) }
    val prefs: Preferences by inject()
    var themeValue by remember {mutableStateOf(false)}

    LaunchedEffect(key1 = Theme.Light) {
        themeValue = prefs.getIsDarkTheme().first()
    }

    val infiniteTransition = rememberInfiniteTransition(label = "IconZoomAnimInfinite")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = if (isAnimating) 1.6f else 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "IconZoomAnim"
    )
    val infiniteRotationTransition = rememberInfiniteTransition(label = "IconRotationAnimInfinite")
    val angle by infiniteRotationTransition.animateFloat(
        initialValue = 0F,
        targetValue = 360F,
        animationSpec = infiniteRepeatable(
            animation = tween(6000, easing = LinearEasing)
        ), label = "IconRotationAnim"
    )
    val inhaleExhaleScale = 1.6f
    val animationTolerance = 0.01f


    var isExhalePhase by remember { mutableStateOf(false) }

    LaunchedEffect(isAnimating) {
        launch {
            while (isAnimating) {
                if (!isExhalePhase) {
                    while (scale < inhaleExhaleScale - animationTolerance) {
                        delay(16)
                    }
                    breathingText = "Exhale"
                    isExhalePhase = true
                } else {
                    while (scale > 1f + animationTolerance) {
                        delay(16)
                    }
                    breathingText = "Inhale"
                    isExhalePhase = false
                    cycleCount++
                }
            }
        }
    }

    MainScreenWithBackground(
        addScaffolding = true,
        topBar = {
            DefaultTopAppBar(title = R.string.breathing) {
                navController.popBackStack()
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .clickable(indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = {}
                )
            ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box{
                Image(
                    painter = painterResource(
                        id = R.drawable.spikes_bg),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier =
                    if(isAnimating){
                        Modifier
                            .scale(scale)
                            .size(210.dp)
                    }
                    else{
                        Modifier
                    }
                )

                Image(
                    painter = painterResource(
                        id =
                        if(themeValue){
                            R.drawable.breathe_rounded_spikes_dark
                        }
                        else {
                            R.drawable.breathe_rounded_spikes
                        }),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = if(isAnimating){
                        Modifier
                            .align(Alignment.Center)
                            .size(165.dp)
                            .graphicsLayer(rotationZ = angle)
                    }
                    else{
                        Modifier
                            .align(Alignment.Center)
                    }
                )

                Image(
                    painter = painterResource(
                        id = R.drawable.breathe_main_grad),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(135.dp)
                )

                Text(text = breathingText,
                    color = Color.White,
                    fontFamily = PoppinsFontMedium,
                    modifier = Modifier
                        .align(Alignment.Center)
                )

            }

            Spacer(modifier = Modifier.height(80.dp))

            Text(
                text = "Completed Rounds: $cycleCount",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontFamily = PoppinsFontMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .clickable(indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = {
                            isAnimating = !isAnimating
                            breathingText = "Inhale"
                        }
                    )
            ) {
                Box(
                    modifier =Modifier.background(
                        if(isAnimating){
//                            AppMainColorTransparent
                            AppMainColor
                        }
                        else{
                            AppMainColor
                        }
                    ),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier.padding(30.dp, 10.dp, 30.dp, 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = if (isAnimating) "Stop" else "Start",
                            fontSize = 13.sp,
                            fontFamily = PoppinsFontRegular,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text="Reset",
                modifier = Modifier
                    .padding(20.dp)
                    .clickable(indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = {
                            cycleCount = 0
                            isAnimating = false
                            breathingText = "Inhale"
                        }
                    ),
                fontFamily = PoppinsFontMedium,
                color = MaterialTheme.colorScheme.primary,
                style = TextStyle(textDecoration = TextDecoration.Underline)
            )
        }
    }
}