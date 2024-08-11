package com.motivation.inspiria.presentation.main_screen

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Left
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Right
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.motivation.inspiria.core.enums.BottomNavItems
import com.motivation.inspiria.core.enums.DrawerItems
import com.motivation.inspiria.core.enums.Theme
import com.motivation.inspiria.presentation.main_screen.components.HomeScreen
import com.motivation.inspiria.presentation.categories_screen.CategoriesScreen
import com.motivation.inspiria.presentation.explore_screen.ExploreScreen
import com.motivation.inspiria.presentation.favorite_screen.FavouriteScreen
import com.motivation.inspiria.presentation.main_screen.components.BottomNavBarMainScreen
import com.motivation.inspiria.core.presentation.DefaultTopAppBar
import com.motivation.inspiria.core.presentation.MainScreenWithBackground
import com.motivation.inspiria.core.presentation.TopAppBarMainScreen
import com.motivation.inspiria.core.presentation.permissions.RequestPermission
import com.motivation.inspiria.core.utils.Preferences
import com.motivation.inspiria.core.utils.SdkHelper
import com.motivation.inspiria.presentation.main_screen.components.DrawerContent
import com.motivation.inspiria.presentation.main_screen.components.ThemeDialog
import kotlinx.coroutines.launch
import org.koin.androidx.compose.inject
import kotlin.system.exitProcess

@Composable
fun MainScreen(navController: NavHostController, changeTheme:(Boolean)->Unit) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var selectedScreen by rememberSaveable { mutableStateOf(BottomNavItems.Home) }
    val coroutineScope = rememberCoroutineScope()
    var showThemeDialog by remember { mutableStateOf(false) }
    val prefs: Preferences by inject()


    BackHandler {
        if(selectedScreen!= BottomNavItems.Home){
            selectedScreen = BottomNavItems.Home
        }
        else {
            exitProcess(1)
        }
    }


    ModalNavigationDrawer(
        drawerContent = {
            DrawerContent(
                onItemSelected = { item ->
                    when(item){
                        DrawerItems.Theme -> { showThemeDialog = true }
                        DrawerItems.EnableNotification -> showThemeDialog=false
                        DrawerItems.ScheduleNotification -> navController.navigate("ScheduleNotificationsScreen")
                        DrawerItems.Affirmations -> navController.navigate("AffirmationsScreen")
                    }
                    // Handle drawer item click
                    coroutineScope.launch {
                        drawerState.close()
                    }
                    Log.d("DrawerItem", "Selected: $item")
                }
            )
        },
        drawerState = drawerState,
        gesturesEnabled = true
    ){
        MainScreenWithBackground(
            addScaffolding = true,
            topBar = {
                if(selectedScreen == BottomNavItems.Home){
                    TopAppBarMainScreen{
                        coroutineScope.launch { drawerState.open() }
                    }
                }
                else{
                    DefaultTopAppBar(title = selectedScreen.navName) {
                        selectedScreen = BottomNavItems.Home
                    }
                }

            },
            bottomBar = {
                BottomNavBarMainScreen(
                    selectedScreen = selectedScreen,
                    onItemClick = {
                        selectedScreen = it
                    }
                )
            })
        { paddingValues ->

            AnimatedContent(
                targetState = selectedScreen,
                transitionSpec = {
                    slideIntoContainer(
                        animationSpec = tween(300, easing = EaseIn),
                        towards = Right
                    ).togetherWith(
                        slideOutOfContainer(
                            animationSpec = tween(300, easing = EaseOut),
                            towards = Left
                        )
                    )
                },
                label = "Bottom Nav Items")
            { targetState ->
                when (targetState) {
                    BottomNavItems.Home -> {
                        HomeScreen(navController = navController, paddingValues){
                            selectedScreen = BottomNavItems.Categories
                        }
                    }
                    BottomNavItems.Explore -> {
                        ExploreScreen(navController = navController, paddingValues)
                    }
                    BottomNavItems.Categories -> {
                        CategoriesScreen(navController = navController, paddingValues)
                    }
                    BottomNavItems.Favorites -> {
                        FavouriteScreen(navController = navController, paddingValues)
                    }
                }
            }

            ThemeDialog(
                openDialog = showThemeDialog,
                onDismiss = {
                    showThemeDialog = false
                },
                onConfirm = { it->
                    showThemeDialog = false
                    when(it){
                        Theme.Light.name ->{
                            coroutineScope.launch {
                                prefs.setIsDarkTheme(false)
                            }
                            changeTheme(false)
                        }
                        Theme.Dark.name -> {
                            coroutineScope.launch {
                                prefs.setIsDarkTheme(true)
                            }
                            changeTheme(true)
                        }
                    }
                }
            )

        }
    }
    RequestPostNotificationPermission(LocalContext.current)
}



@Composable
fun RequestPostNotificationPermission(context: Context) {
    if (SdkHelper.isTiramisu() && ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_DENIED
    ) {
        RequestPermission(
            permissions = arrayOf(Manifest.permission.POST_NOTIFICATIONS),
            allPermissionsGranted = {
                Toast.makeText(
                    context,
                    "Notification permission granted",
                    Toast.LENGTH_SHORT
                ).show()
            },
            permissionsDenied = {
                Toast.makeText(context, "Denied $it", Toast.LENGTH_SHORT).show()
            }
        )
    }
}
