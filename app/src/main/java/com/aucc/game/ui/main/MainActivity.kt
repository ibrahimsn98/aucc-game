package com.aucc.game.ui.main

import android.os.Bundle
import android.support.design.widget.Snackbar
import com.aucc.game.R
import com.aucc.game.base.BaseActivity
import com.aucc.game.databinding.ActivityMainBinding
import com.aucc.game.util.AnimUtils

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun layoutRes(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AnimUtils.hackAnimatedText(this, binding.title)

        Snackbar.make(binding.coordinator, "Deneme", Snackbar.LENGTH_LONG).show()
    }
}
