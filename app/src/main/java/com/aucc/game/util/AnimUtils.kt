package com.aucc.game.util

import android.app.Activity
import android.widget.TextView
import java.util.*
import kotlin.concurrent.fixedRateTimer
import kotlin.concurrent.schedule

class AnimUtils {

    companion object {

        fun hackAnimatedText(a: Activity, tv: TextView) {
            val array = tv.text.toString().toCharArray()

            fixedRateTimer(name = "anim-timer", initialDelay = 1000, period = 1000) {
                val rand = (2 until array.size - 2).shuffled().first()
                val temp = array.clone()
                if (temp[rand] != ' ') {
                    temp[rand] = rand.toString().toCharArray().last()
                    a.runOnUiThread { tv.text = String(temp) }

                    Timer("anim-de-timer", false).schedule(50) {
                        a.runOnUiThread { tv.text = String(array) }
                    }
                }
            }
        }
    }
}