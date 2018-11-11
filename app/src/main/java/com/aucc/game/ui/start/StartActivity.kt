package com.aucc.game.ui.start

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import com.aucc.game.ui.main.MainActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class StartActivity : DaggerAppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(StartViewModel::class.java)

        viewModel.status.observe(this, Observer { status ->
            if (status != null)
                if (status) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
        })
    }
}
