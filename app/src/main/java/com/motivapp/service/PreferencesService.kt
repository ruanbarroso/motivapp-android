package com.motivapp.service

import android.content.Context
import com.google.gson.Gson
import com.motivapp.model.User

object PreferencesService {

    private fun getSharedPreferences(context: Context) = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    fun getUser(context: Context): User? {
        val sharedPreferences = getSharedPreferences(context)
        if (sharedPreferences.contains("user")) {
            try {
                return Gson().fromJson(sharedPreferences.getString("user", null), User::class.java) as User
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
        return null
    }

    fun putUser(context: Context, user: User) {
        val sharedPreferences = getSharedPreferences(context)
        sharedPreferences.edit().putString("user", Gson().toJson(user)).apply()
    }

    fun removeUser(context: Context) {
        val sharedPreferences = getSharedPreferences(context)
        sharedPreferences.edit().remove("user").apply()
    }

}