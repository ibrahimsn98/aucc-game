package com.aucc.game.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import javax.inject.Inject

class PrefUtils @Inject constructor(context: Context) {

    private val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)

    fun setLevelCompleted(stepId: Int) {
        val oldSet = getCompletedLevelIds()
        oldSet.add(stepId.toString())
        prefs.edit().putStringSet("completed_levels", oldSet).apply()
    }

    fun getCompletedLevelIds(): MutableSet<String> {
        return prefs.getStringSet("completed_levels", setOf<String>())!!
    }
}