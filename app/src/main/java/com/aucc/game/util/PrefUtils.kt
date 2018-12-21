package com.aucc.game.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import javax.inject.Inject

class PrefUtils @Inject constructor(context: Context) {

    private val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)

    fun setLevelCompleted(levelId: Int) {
        val oldSet = getCompletedLevelIds()
        oldSet.add(levelId.toString())
        prefs.edit().putStringSet("completed_levels", oldSet).apply()
    }

    fun getCompletedLevelIds(): MutableSet<String> {
        return prefs.getStringSet("completed_levels", mutableSetOf<String>()) ?: mutableSetOf()
    }
}