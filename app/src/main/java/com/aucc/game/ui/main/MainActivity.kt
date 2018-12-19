package com.aucc.game.ui.main

import android.animation.Animator
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
import com.aucc.game.databinding.ActivityMainBinding
import com.aucc.game.ui.game.GameViewModel
import com.aucc.game.util.AnimUtils
import com.aucc.game.util.BottomNavigationViewUtil
import javax.inject.Inject
import android.support.design.widget.BottomSheetBehavior
import android.widget.LinearLayout
import com.aucc.game.rest.model.Level

class MainActivity : BaseActivity<ActivityMainBinding>(), NavController.OnDestinationChangedListener {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var navController: NavController
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    private var inGame = false

    override fun layoutRes(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AnimUtils.hackAnimatedText(this, binding.title)

        setSupportActionBar(binding.toolbar)

        navController = findNavController(this, R.id.host)
        binding.bottomNavigationView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener(this)

        BottomNavigationViewUtil.disableShiftMode(binding.bottomNavigationView)

        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
    }

    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
        binding.appBarLayout.setExpanded(true, true)
    }

    fun openLevel(level: Level) {
        val gameViewModel = ViewModelProviders.of(this, viewModelFactory).get(GameViewModel::class.java)
        gameViewModel.level.value = level
        navController.navigate(R.id.levels_to_game)
    }

    private val animListener = object: Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {}

        override fun onAnimationCancel(animation: Animator?) {}

        override fun onAnimationStart(animation: Animator?) {}

        override fun onAnimationEnd(animation: Animator?) {
            if (inGame) bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    fun setBottomNavVisibility(visible: Boolean) {
        inGame = !visible

        if (visible) {
            binding.bottomNavigationView.animate().translationY(0f)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        } else {
            binding.bottomNavigationView.animate().translationY(binding.bottomNavigationView.height.toFloat()).setListener(animListener)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> navController.navigateUp()
        }

        return super.onOptionsItemSelected(item)
    }
}
