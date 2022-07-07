package kr.eddymango.exercisefam.data

import android.app.Application

class MysharedPreferences:Application() {

    companion object{
        lateinit var prefs:PreferenceUtil

    }

    override fun onCreate() {
        prefs = PreferenceUtil(applicationContext)
        super.onCreate()
    }
}