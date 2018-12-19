package com.aucc.game.ui.start

import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.os.Bundle
import com.aucc.game.ui.main.MainActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class StartActivity : DaggerAppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
