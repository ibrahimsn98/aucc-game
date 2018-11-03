package com.aucc.game.di.module

import com.aucc.game.ui.home.HomeFragment
import com.aucc.game.ui.levels.LevelsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainBindingModule {

    @ContributesAndroidInjector
    internal abstract fun provideHomeFragmentFactory(): HomeFragment

    @ContributesAndroidInjector
    internal abstract fun provideLevelsFragmentFactory(): LevelsFragment
}