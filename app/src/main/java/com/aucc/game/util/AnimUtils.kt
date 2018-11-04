package com.aucc.game.util

import android.app.Activity
import android.widget.TextView
import kotlin.concurrent.fixedRateTimer

class AnimUtils {

    companion object {

        fun hackAnimatedText(a: Activity, tv: TextView) {
            val array = tv.text.toString().toCharArray()
            var isAnimated = false

            fixedRateTimer(name = "anim-timer", initialDelay = 1000, period = 1000) {
                if (!isAnimated) {
                    val rand = (2 until array.size - 2).shuffled().first()
                    val temp = array.clone()
                    if (temp[rand] != ' ') {
                        temp[rand] = rand.toString().toCharArray().last()
                        a.runOnUiThread { tv.text = String(temp) }
                        isAnimated = true
                    }
                }
            }

            fixedRateTimer(name = "anim-timer-prev", initialDelay = 1015, period = 1015) {
                if (isAnimated) {
                    a.runOnUiThread { tv.text = String(array) }
                    isAnimated = false
                }
            }
        }
    }
}