package com.example.newdbapp.Utility

import android.content.Context
import java.util.UUID

fun getOrCreateAppInstanceId(context: Context): String {
    val sharedPrefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    var uniqueId = sharedPrefs.getString("installation_id", null)

    if (uniqueId == null) {
        uniqueId = UUID.randomUUID().toString()
        sharedPrefs.edit().putString("installation_id", uniqueId).apply()
    }
    return uniqueId
}