package com.aucc.game.ui.game

import android.app.Dialog
import android.support.annotation.NonNull
import android.support.design.widget.BottomSheetDialogFragment
import android.view.View
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.CoordinatorLayout
import android.widget.Button
import com.aucc.game.R

class GameBottomSheet : BottomSheetDialogFragment() {

    private val bottomSheetBehaviorCallback = object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onStateChanged(@NonNull bottomSheet: View, newState: Int) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN)
                dismiss()
        }

        override fun onSlide(@NonNull bottomSheet: View, slideOffset: Float) {

        }
    }

    override fun setupDialog(dialog: Dialog, style: Int) {
        val contentView = View.inflate(context, R.layout.dialog_game_step, null)
        dialog.setContentView(contentView)

        val params = (contentView.parent as View).layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior

        contentView.findViewById<Button>(R.id.close).setOnClickListener {
            dismiss()
        }

        if (behavior != null && behavior is BottomSheetBehavior<*>) {
            behavior.setBottomSheetCallback(bottomSheetBehaviorCallback)
        }
    }
}