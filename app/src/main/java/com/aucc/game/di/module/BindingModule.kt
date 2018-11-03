package com.aucc.game.di.module

import com.aucc.game.StartActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BindingModule {

    @ContributesAndroidInjector
    internal abstract fun bindStartActivity(): StartActivity
}