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

    fun getCompletedLevelCount(): Int {
        return getCompletedLevelIds().size
    }

    fun setSystemNotificationsEnabled(isEnabled: Boolean) {
        prefs.edit().putBoolean("system_notifications", isEnabled).apply()
    }

    fun isSystemNotificationsEnabled(): Boolean {
        return prefs.getBoolean("system_notifications", true)
    }

    fun setLevelCount(count: Int) {
        prefs.edit().putInt("level_count", count).apply()
    }

    fun getLevelCount(): Int {
        return prefs.getInt("level_count", 0)
    }
}