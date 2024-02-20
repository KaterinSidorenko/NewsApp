package com.example.chagecolor

import android.content.Context


fun saveCredentials(context: Context, email: String, password: String) {
    val sharedPrefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    with(sharedPrefs.edit()) {
        putString("email", email)
        putString("password", password)
        apply()
    }
}


fun getSavedCredentials(context: Context): Pair<String, String>? {
    val sharedPrefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    val email = sharedPrefs.getString("email", null)
    val password = sharedPrefs.getString("password", null)
    return if (email != null && password != null) {
        Pair(email, password)
    } else {
        null
    }
}

fun clearSharedPreferences(context: Context) {
    val sharedPrefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    with(sharedPrefs.edit()) {
        clear()  // Очищаем все сохраненные значения
        apply()
    }
}