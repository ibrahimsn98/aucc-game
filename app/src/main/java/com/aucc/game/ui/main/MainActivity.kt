package com.aucc.game.ui.main

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.aucc.game.R
import com.aucc.game.base.BaseActivity
import com.aucc.game.data.level.Level
import com.aucc.game.databinding.ActivityMainBinding
import com.aucc.game.ui.game.GameViewModel
import com.aucc.game.util.AnimUtils
import com.aucc.game.util.BottomNavigationViewUtil
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>(), NavController.OnNavigatedListener {

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
        binding.bottomNavigationView.setupWithNavController(navController)
        navController.addOnNavigatedListener(this)

        BottomNavigationViewUtil.disableShiftMode(binding.bottomNavigationView)
    }

    override fun onNavigated(controller: NavController, destination: NavDestination) {
        binding.appBarLayout.setExpanded(true, true)
    }

    fun openLevel(level: Level) {
        val gameViewModel = ViewModelProviders.of(this, viewModelFactory).get(GameViewModel::class.java)
        gameViewModel.level.value = level
        navController.navigate(R.id.levels_to_game)
    }

    fun setBottomNavVisibility(visible: Boolean) {
        if (visible) binding.bottomNavigationView.animate().translationY(0f)
        else binding.bottomNavigationView.animate().translationY(binding.bottomNavigationView.height.toFloat())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> navController.navigateUp()
        }

        return super.onOptionsItemSelected(item)
    }
}
