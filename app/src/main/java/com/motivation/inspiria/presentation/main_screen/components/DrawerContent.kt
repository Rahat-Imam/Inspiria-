package com.motivation.inspiria.presentation.main_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.motivation.inspiria.R
import com.motivation.inspiria.core.enums.DrawerItems
import com.motivation.inspiria.core.utils.Preferences
import com.motivation.inspiria.ui.theme.AppMainColor
import com.motivation.inspiria.ui.theme.PoppinsFontMedium
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.androidx.compose.inject

@Composable
fun DrawerContent(
    onItemSelected: (DrawerItems) ->Unit
) {

    Box(
        Modifier
            .fillMaxHeight()
            .width(250.dp)
            .background(MaterialTheme.colorScheme.background)
    ){
        Column {
            
            Spacer(modifier = Modifier.height(80.dp))

            Image(
                painter = painterResource(
                    id = R.drawable.logo
                ),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(30.dp))

            LazyColumn(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxSize(),
                content = {
                    items(DrawerItems.entries.size) {
                        DrawerItem(DrawerItems.entries[it]){ it ->
                            onItemSelected(it)
                        }
                    }
                }
            )
        }
    }
}


@Composable
fun DrawerItem(
    item: DrawerItems,
    onItemSelected: (DrawerItems) -> Unit,
) {

    var checked by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val prefs: Preferences by inject()
    LaunchedEffect(key1 = Unit) {
        checked = prefs.getIsNotificationEnabled().first()
        
    }


    if(item == DrawerItems.EnableNotification){
        Row{
            Text(
                text = item.itemName,
                fontSize = 13.sp,
                fontFamily = PoppinsFontMedium,
                lineHeight = 12.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier
                    .clickable {
                        onItemSelected(item)
                    }
                    .padding(
                        start = 16.dp,
                        top = 16.dp,
                        bottom = 16.dp
                    )
            )
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = checked,
                onCheckedChange = {
                    coroutineScope.launch {
                        prefs.setNotificationEnabledValue(checked)
                    }
                    checked = it
                },
                colors = SwitchDefaults.colors(
                    checkedBorderColor = AppMainColor,
                    checkedTrackColor = Color.White,
                    checkedThumbColor = AppMainColor,
                    uncheckedThumbColor = Color.Gray
                ),
            )
        }
    }
    else{
        Text(
            text = item.itemName,
            fontSize = 13.sp,
            fontFamily = PoppinsFontMedium,
            lineHeight = 12.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onItemSelected(item)
                }
                .padding(16.dp)
        )
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewDrawer(){
    DrawerContent{

    }
}
