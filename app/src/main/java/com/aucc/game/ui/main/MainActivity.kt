package com.aucc.game.ui.main

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation.findNavController
import com.aucc.game.R
import com.aucc.game.base.BaseActivity
import com.aucc.game.databinding.ActivityMainBinding
import com.aucc.game.ui.game.GameViewModel
import com.aucc.game.util.AnimUtils
import javax.inject.Inject
import android.support.design.widget.Snackbar
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.aucc.game.rest.model.Level
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ActivityMainBinding>(), NavController.OnDestinationChangedListener {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var navController: NavController

    override fun layoutRes(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AnimUtils.hackAnimatedText(this, binding.title)

        setSupportActionBar(binding.toolbar)

        navController = findNavController(this, R.id.host)
        navController.addOnDestinationChangedListener(this)

        setupActionBarWithNavController(navController, AppBarConfiguration(navController.graph))
    }

    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
        binding.appBarLayout.setExpanded(true, true)
    }

    fun openLevel(level: Level) {
        val gameViewModel = ViewModelProviders.of(this, viewModelFactory).get(GameViewModel::class.java)
        gameViewModel.initialize(level)
        navController.navigate(R.id.levels_to_game)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> navController.navigateUp()
        }

        return super.onOptionsItemSelected(item)
    }

    fun showMessage(text: String) {
        Snackbar.make(coordinator, text, Snackbar.LENGTH_SHORT).show()
    }
}
