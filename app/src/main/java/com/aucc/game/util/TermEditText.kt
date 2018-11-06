package com.aucc.game.util

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import android.widget.EditText

class TermEditText : EditText {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )

    private lateinit var callback: TermEditTextCallback

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {

        Log.d("###", keyCode.toString())
        if (keyCode==KeyEvent.KEYCODE_ENTER) {
            callback.onEnterKeyDown()
            return true
        }

        return super.onKeyDown(keyCode, event)
    }

    fun setTermEditTextCallback(callback: TermEditTextCallback) {
        this.callback = callback
    }

    interface TermEditTextCallback {
        fun onEnterKeyDown()
    }
}