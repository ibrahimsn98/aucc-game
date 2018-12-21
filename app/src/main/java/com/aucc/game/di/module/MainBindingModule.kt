package com.aucc.game.di.module

import com.aucc.game.ui.game.GameFragment
import com.aucc.game.ui.levels.LevelsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainBindingModule {

    @ContributesAndroidInjector
    internal abstract fun provideLevelsFragmentFactory(): LevelsFragment

    @ContributesAndroidInjector
    internal abstract fun provideGameFragmentFactory(): GameFragment
}