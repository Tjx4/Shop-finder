package com.entersekt.shops.persistance.sharedPrefs

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.entersekt.outlets.models.City
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class SharedPrefs(private val application: Application) {
    private val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(application)
    private val DATA = "data"

    var cities: List<City>
        get() {
            val json: String? = sharedPreferences.getString(DATA, null)
            val type: Type = object : TypeToken<ArrayList<City>>() {}.type
            return Gson().fromJson(json, type)
        }
        set(cities) {
            val editor = sharedPreferences.edit()
            val connectionsJSONString = Gson().toJson(cities)
            editor.putString(DATA, connectionsJSONString)
            editor.commit()
        }

    companion object{
        fun getInstance(application: Application): SharedPrefs {
            synchronized(this){
                var instance: SharedPrefs? = null

                if(instance == null){
                    instance = SharedPrefs(application)
                }

                return  instance
            }
        }
    }
}