package com.codetech.fullscreennative

import android.app.Activity
import android.content.Intent

fun Activity.newScreen(c: Class<*>, isFinish: Boolean = false) {
    val intent = Intent(this, c)
    if (isFinish) {
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    } else {
        startActivity(intent)
    }
}