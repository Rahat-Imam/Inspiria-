package com.motivation.inspiria.core.extension

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity

fun read(link:String, context: Context){
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
    startActivity(context, intent, null)
}