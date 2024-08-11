package com.motivation.inspiria.core.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.motivation.inspiria.R
import com.motivation.inspiria.ui.theme.PoppinsFontSemiBold


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopAppBar(title :Int, onSettingsClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(title),
                fontSize = 16.sp,
                fontFamily = PoppinsFontSemiBold
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    onSettingsClick()
                }
            ) {
                Icon(
                    painterResource(id = R.drawable.backpress),
                    contentDescription = "Backpress"
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopAppBar(title :String, onSettingsClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 16.sp,
                fontFamily = PoppinsFontSemiBold
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    onSettingsClick()
                }
            ) {
                Icon(
                    painterResource(id = R.drawable.backpress),
                    contentDescription = "Backpress"
                )
            }
        }
    )
}