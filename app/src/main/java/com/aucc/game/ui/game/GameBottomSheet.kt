package com.aucc.game.ui.game

import android.app.Dialog
import android.support.annotation.NonNull
import android.support.design.widget.BottomSheetDialogFragment
import android.view.View
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.CoordinatorLayout
import android.widget.Button
import android.widget.TextView
import com.aucc.game.R

class GameBottomSheet : BottomSheetDialogFragment() {

    private var dialogCallback: DialogCallback? = null
    private var state = false
    private var message = ""
    private var title = ""
    private var button = ""

    private val bottomSheetBehaviorCallback = object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onStateChanged(@NonNull bottomSheet: View, newState: Int) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN)
                dismiss()
        }

        override fun onSlide(@NonNull bottomSheet: View, slideOffset: Float) {

        }
    }

    fun setState(state: Boolean, message: String, dialogCallback: DialogCallback): GameBottomSheet {
        this.state = state
        this.message = message
        this.dialogCallback = dialogCallback

        if (!state) {
            title = "[ Message ]"
            button = "Continue"
        } else {
            title = "[ Completed ]"
            button = "Complete"
        }

        return this
    }

    override fun setupDialog(dialog: Dialog, style: Int) {
        val contentView = View.inflate(context, R.layout.dialog_game_step, null)
        dialog.setContentView(contentView)

        val params = (contentView.parent as View).layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior

        contentView.findViewById<TextView>(R.id.title).text = title
        contentView.findViewById<TextView>(R.id.message).text = message
        contentView.findViewById<Button>(R.id.close).text = button

        contentView.findViewById<Button>(R.id.close).setOnClickListener {
            dismiss()
            if (dialogCallback != null && state) dialogCallback!!.onButtonClicked()
        }

        if (behavior != null && behavior is BottomSheetBehavior<*>) {
            behavior.setBottomSheetCallback(bottomSheetBehaviorCallback)
        }
    }

    interface DialogCallback {
        fun onButtonClicked()
    }
}