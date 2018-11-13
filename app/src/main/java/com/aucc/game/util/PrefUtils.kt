package com.aucc.game.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import javax.inject.Inject

class PrefUtils @Inject constructor(context: Context) {

    private val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)

    fun addCompletedLevel(levelId: String) {
        val new = getCompletedLevels()
        new.add(levelId)
        prefs.edit().putStringSet("levels_completed", new).apply()
    }

    fun getCompletedLevels(): MutableSet<String> {
        return prefs.getStringSet("levels_completed", mutableSetOf())!!
    }
}