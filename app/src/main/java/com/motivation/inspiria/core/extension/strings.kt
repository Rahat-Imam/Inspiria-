package com.motivation.inspiria.core.extension

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast


infix fun Context.share(shareable: String) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT,shareable)
        type = "text/plain"
    }

    this.startActivity(Intent.createChooser(sendIntent,"Inspiria"))
}

infix fun Context.copy(shareable: String) {
    val clipboard = this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    clipboard.setPrimaryClip(ClipData.newPlainText("Copied Text", shareable))
    Toast.makeText(this, "Text copied to clipboard!", Toast.LENGTH_SHORT).show()
}