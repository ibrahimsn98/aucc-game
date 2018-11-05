package com.aucc.game.ui.main

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.MenuItem
import com.aucc.game.R
import com.aucc.game.base.BaseActivity
import com.aucc.game.data.level.Level
import com.aucc.game.databinding.ActivityMainBinding
import com.aucc.game.ui.game.GameFragment
import com.aucc.game.ui.game.GameViewModel
import com.aucc.game.ui.home.HomeFragment
import com.aucc.game.ui.levels.LevelsFragment
import com.aucc.game.util.AnimUtils
import com.aucc.game.util.BottomNavigationViewUtil
import java.util.ArrayList
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>(), BottomNavigationView.OnNavigationItemSelectedListener {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private val callbacks = ArrayList<MainCallback>()
    private var current = -1

    override fun layoutRes(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AnimUtils.hackAnimatedText(this, binding.title)

        setSupportActionBar(binding.toolbar)
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(this)

        BottomNavigationViewUtil.disableShiftMode(binding.bottomNavigationView)

        if (savedInstanceState == null)
            binding.bottomNavigationView.selectedItemId = R.id.home
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        loadFragment(when (item.itemId) {
            0 -> HomeFragment()
            R.id.levels -> LevelsFragment()
            else ->  HomeFragment()
        }, item.itemId)

        return true
    }

    fun openLevel(level: Level) {
        openToolbar()
        val gameViewModel = ViewModelProviders.of(this, viewModelFactory).get(GameViewModel::class.java)
        gameViewModel.level.value = level
        supportFragmentManager.beginTransaction().addToBackStack("game").replace(R.id.host, GameFragment()).commit()
    }

    fun clearFragmentStack() {
        supportFragmentManager.popBackStack("game", FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    private fun loadFragment(fragment: Fragment, id: Int) {
        openToolbar()
        if (id != current) {
            current = id
            supportFragmentManager.beginTransaction().replace(R.id.host, fragment).commit()
        } else {
            for (mainCallback in callbacks) mainCallback.onMenuReselected()
        }
    }

    fun setBottomNavVisibility(visible: Boolean) {
        if (visible) binding.bottomNavigationView.animate().translationY(0f)
        else binding.bottomNavigationView.animate().translationY(binding.bottomNavigationView.height.toFloat())
    }

    private fun openToolbar() {
        binding.appBarLayout.setExpanded(true, true)
    }

    fun addMainCallback(mainCallback: MainCallback) {
        callbacks.add(mainCallback)
    }

    fun removeMainCallback(mainCallback: MainCallback) {
        callbacks.remove(mainCallback)
    }

    interface MainCallback {
        fun onMenuReselected()
    }
}
