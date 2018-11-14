package com.aucc.game.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.aucc.game.data.level.Level
import com.google.gson.JsonObject
import javax.inject.Inject

class PrefUtils @Inject constructor(context: Context) {

    private val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)

    fun addCompletedLevel(level: Level) {
        val new = getCompletedLevels()

        val obj = JsonObject()
        obj.addProperty("id", level.id)
        obj.addProperty("title", level.title)
        obj.addProperty("time", System.currentTimeMillis()/1000)

        new.add(obj.asString)
        prefs.edit().putStringSet("levels_completed_ids", level.id).putStringSet("levels_completed", new).apply()
    }

    fun getCompletedLevels(): MutableSet<String> {
        return prefs.getStringSet("levels_completed", mutableSetOf())!!
    }
}