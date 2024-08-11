package com.motivation.inspiria.presentation.schedule_notifications_screen

import android.app.TimePickerDialog
import android.widget.TimePicker
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
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.motivation.inspiria.core.QuotesViewModel
import com.motivation.inspiria.core.presentation.DefaultTopAppBar
import com.motivation.inspiria.core.presentation.MainScreenWithBackground
import com.motivation.inspiria.ui.theme.AppMainColor
import com.motivation.inspiria.ui.theme.PoppinsFontMedium
import com.motivation.inspiria.ui.theme.PoppinsFontRegular
import org.koin.androidx.compose.inject
import java.util.Calendar

@Composable
fun ScheduleNotificationsScreen(navController: NavController) {

    MainScreenWithBackground(
        addScaffolding = true,
        topBar = {
            DefaultTopAppBar("Schedule Notifications"){
                navController.popBackStack()
            }
        }
    ){ paddingValues->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){
            ScheduleNotifications()
        }
    }
}

@Composable
fun ScheduleNotifications(){

    val viewModel : QuotesViewModel by inject()
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val hour = remember { mutableStateOf(calendar.get(Calendar.HOUR_OF_DAY)) }
    val minute = remember { mutableStateOf(calendar.get(Calendar.MINUTE)) }
    val time = remember { mutableStateOf("") }
    var visibility by remember { mutableStateOf(false) }

    val timePickerDialog = TimePickerDialog(
        context,
        { _: TimePicker, selectedHour: Int, selectedMinute: Int ->
            hour.value = selectedHour
            minute.value = selectedMinute
            time.value = String.format("%02d:%02d", selectedHour, selectedMinute)
        }, hour.value, minute.value, false
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        Text(
            text = "Schedule notification on custom time",
            fontSize = 14.sp,
            fontFamily = PoppinsFontMedium,
            lineHeight = 30.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier
        )
        if(visibility){
            Text(
                text = "Selected Time: ${time.value}",
                fontSize = 14.sp,
                fontFamily = PoppinsFontMedium,
                lineHeight = 30.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
        }


        Card(
            modifier = Modifier
                .padding(5.dp)
                .clickable(indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = {
                        visibility = true
                        timePickerDialog.show()
                    }
                )
        ) {
            Box(
                modifier = Modifier.background(AppMainColor),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.padding(40.dp, 15.dp, 40.dp, 15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Pick Time",
                        fontSize = 12.sp,
                        fontFamily = PoppinsFontRegular,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
            }

        }

        Spacer(
            modifier = Modifier.height(50.dp)
        )


        if(visibility){
            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .clickable(indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = {
                            viewModel.scheduleNotification(hour.value, minute.value)
                        }
                    )
            ) {
                Box(
                    modifier = Modifier.background(AppMainColor),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier.padding(40.dp, 15.dp, 40.dp, 15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Schedule",
                            fontSize = 12.sp,
                            fontFamily = PoppinsFontRegular,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }
                }

            }

        }

    }

}


@Preview(showBackground = true)
@Composable
fun PreviewScheduleNotifications(){
    ScheduleNotificationsScreen(rememberNavController())
}