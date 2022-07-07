package kr.eddymango.exercisefam.data

import android.content.Context
import android.content.SharedPreferences

class MysharedPreferences(context: Context) {

    val PREFS_FILENAME = "prefs"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)





}