package kr.eddymango.exercisefam.data

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context:Context) {

    private val prefs:SharedPreferences = context.getSharedPreferences("UserData",0)

    fun getString(key:String, defValue:String):String{
        return prefs.getString(key,defValue).toString()
    }
    fun getBoolean(key:String,defValue:Boolean): Boolean {
        return prefs.getBoolean(key,defValue)
    }

    fun setString(key:String,str:String){
        prefs.edit().putString(key,str).apply()
    }
    fun setBoolean(key:String,bln:Boolean){
        prefs.edit().putBoolean(key,bln).apply()
    }



}