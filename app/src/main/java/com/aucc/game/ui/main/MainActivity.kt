package com.aucc.game.ui.main

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.aucc.game.R
import com.aucc.game.base.BaseActivity
import com.aucc.game.databinding.ActivityMainBinding
import com.aucc.game.ui.game.GameViewModel
import com.aucc.game.util.AnimUtils
import javax.inject.Inject
import android.support.design.widget.Snackbar
import android.view.Menu
import androidx.navigation.NavDestination
import com.aucc.game.rest.model.Level
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ActivityMainBinding>(), NavController.OnDestinationChangedListener {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var navController: NavController
    private var current = 0

    override fun layoutRes(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AnimUtils.hackAnimatedText(this, binding.title)

        setSupportActionBar(binding.toolbar)

        binding.back.setOnClickListener { navController.navigateUp() }

        navController = findNavController(this, R.id.host)
        navController.addOnDestinationChangedListener(this)
    }

    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
        current = destination.id
        if (destination.id != R.id.levelsFragment) {
            binding.title.animate().translationX((binding.back.width * 1.5).toFloat()).setDuration(200).start()
            binding.back.animate().scaleX(1F).scaleY(1F).setDuration(200).start()
        } else {
            binding.title.animate().translationX(0F).setDuration(200).start()
            binding.back.animate().scaleX(0F).scaleY(0F).setDuration(200).start()
        }
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_profile)
            when (current) {
                R.id.levelsFragment -> navController.navigate(R.id.levels_to_profile)
                R.id.gameFragment -> {
                    navController.navigateUp()
                    navController.navigate(R.id.levels_to_profile)
                }
            }
        else if (item.itemId == android.R.id.home)
            navController.navigateUp()

        return super.onOptionsItemSelected(item)
    }

    fun showMessage(text: String) {
        Snackbar.make(coordinator, text, Snackbar.LENGTH_SHORT).show()
    }
}
