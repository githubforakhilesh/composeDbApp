package com.example.newdbapp.Utility

import android.content.Context
import android.provider.Settings
import java.util.UUID

/**
 * Returns the hardware-based Android ID.
 * This ID is stable across app reinstalls but can change if the device is factory reset.
 */
fun getDeviceId(context: Context): String {
    return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID) ?: ""
}

fun getOrCreateAppInstanceId(context: Context): String {
    val sharedPrefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    var uniqueId = sharedPrefs.getString("installation_id", null)

    if (uniqueId == null) {
        uniqueId = UUID.randomUUID().toString()
        sharedPrefs.edit().putString("installation_id", uniqueId).apply()
    }
    return uniqueId
}