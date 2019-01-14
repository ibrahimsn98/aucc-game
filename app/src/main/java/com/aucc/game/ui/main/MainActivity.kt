package com.aucc.game.ui.main

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.aucc.game.R
import com.aucc.game.base.BaseActivity
import com.aucc.game.databinding.ActivityMainBinding
import com.aucc.game.ui.game.GameViewModel
import javax.inject.Inject
import android.support.design.widget.Snackbar
import android.view.Menu
import com.aucc.game.rest.model.Level
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ActivityMainBinding>() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var navController: NavController

    override fun layoutRes(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navController = findNavController(this, R.id.host)
    }

    fun openLevel(level: Level) {
        val gameViewModel = ViewModelProviders.of(this, viewModelFactory).get(GameViewModel::class.java)
        gameViewModel.initialize(level)
        navController.navigate(R.id.levels_to_game)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    fun showMessage(text: String) {
        Snackbar.make(coordinator, text, Snackbar.LENGTH_SHORT).show()
    }
}
