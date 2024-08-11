package com.motivation.inspiria.presentation.main_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.motivation.inspiria.core.enums.Theme
import com.motivation.inspiria.core.utils.Preferences
import com.motivation.inspiria.ui.theme.PoppinsFontMedium
import com.motivation.inspiria.ui.theme.PoppinsFontRegular
import kotlinx.coroutines.flow.first
import org.koin.androidx.compose.inject

@Composable
fun ThemeDialog(openDialog: Boolean, onConfirm:(String) -> Unit, onDismiss: () -> Unit) {

    val prefs: Preferences by inject()
    var selectedOption by remember { mutableStateOf(Theme.Light.name) }

    LaunchedEffect(key1 = Unit) {
        val themeValue = prefs.getIsDarkTheme().first()
        if(themeValue) selectedOption = Theme.Dark.name
        else selectedOption = Theme.Light.name
    }

    if (openDialog) {
        AlertDialog(
            backgroundColor = MaterialTheme.colorScheme.background,
            onDismissRequest = onDismiss,
            title = {
                Text(
                    text = "Choose Theme",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 14.sp,
                    fontFamily = PoppinsFontMedium,
                    lineHeight = 23.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(4.dp))
            },
            text = {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ){
                        androidx.compose.material.Text(
                            text = Theme.Light.name,
                            fontFamily = PoppinsFontRegular,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .weight(1f)
                        )
                        RadioButton(
                            selected = selectedOption == Theme.Light.name,
                            onClick = { selectedOption = Theme.Light.name },
                            modifier = Modifier.align(Alignment.CenterVertically),
                            colors = RadioButtonColors(
                                selectedColor = MaterialTheme.colorScheme.primary,
                                unselectedColor = Color.LightGray,
                                disabledSelectedColor =  Color.LightGray,
                                disabledUnselectedColor =  Color.LightGray
                            )
                        )
                    }

                    Row{
                        androidx.compose.material.Text(
                            text = Theme.Dark.name,
                            fontFamily = PoppinsFontRegular,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .weight(1f)
                        )
                        RadioButton(
                            selected = selectedOption == Theme.Dark.name,
                            onClick = { selectedOption = Theme.Dark.name },
                            modifier = Modifier.align(Alignment.CenterVertically),
                            colors = RadioButtonColors(
                                selectedColor = MaterialTheme.colorScheme.primary,
                                unselectedColor = Color.LightGray,
                                disabledSelectedColor =  Color.LightGray,
                                disabledUnselectedColor =  Color.LightGray
                            )
                        )
                    }

                }
            },
            confirmButton = {
                TextButton(onClick = { onConfirm(selectedOption) }) {
                    Text(
                        text = "OK",
                        fontFamily = PoppinsFontRegular,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text(
                        text = "Cancel",
                        fontFamily = PoppinsFontRegular,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                }
            }
        )
    }
}
