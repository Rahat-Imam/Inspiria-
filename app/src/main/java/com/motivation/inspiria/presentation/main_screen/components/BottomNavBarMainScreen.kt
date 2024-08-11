package com.motivation.inspiria.presentation.main_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.motivation.inspiria.core.enums.BottomNavItems
import com.motivation.inspiria.ui.theme.PoppinsFontRegular

@Composable
fun BottomNavBarMainScreen(
    selectedScreen: BottomNavItems,
    onItemClick: (BottomNavItems) -> Unit
) {

    Box(
        Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .height(100.dp)
    ){

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(topStartPercent = 20, topEndPercent = 20)
                ),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavItems(selectedScreen = selectedScreen, onItemClick = onItemClick)
        }

    }

}


@Composable
fun NavItems(
    selectedScreen: BottomNavItems,
    onItemClick: (BottomNavItems) -> Unit
) {
    BottomNavItems.entries.forEach {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null
                ) { onItemClick(it) },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter =
                painterResource
                    (
                    id =
                    if (selectedScreen == it)
                        it.selectedIcon
                    else
                        it.unSelectedIcon),
                contentDescription = null,
                tint = if(selectedScreen == it){
                    MaterialTheme.colorScheme.primary
                }
                else{
                    MaterialTheme.colorScheme.onBackground
                },
                modifier = Modifier.size(22.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewBottomNavBarMainScreen() {
    BottomNavBarMainScreen(BottomNavItems.Home){

    }
}
