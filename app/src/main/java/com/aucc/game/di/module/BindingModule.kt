package com.aucc.game.di.module

import com.aucc.game.ui.main.MainActivity
import com.aucc.game.ui.start.StartActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BindingModule {

    @ContributesAndroidInjector
    internal abstract fun bindStartActivity(): StartActivity

    @ContributesAndroidInjector(modules = [MainBindingModule::class])
    internal abstract fun bindMainActivity(): MainActivity
}