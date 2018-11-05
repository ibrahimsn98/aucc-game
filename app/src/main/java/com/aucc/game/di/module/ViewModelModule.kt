package com.aucc.game.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.aucc.game.di.util.ViewModelFactory
import com.aucc.game.di.util.ViewModelKey
import com.aucc.game.ui.game.GameViewModel
import com.aucc.game.ui.levels.LevelsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LevelsViewModel::class)
    abstract fun bindLevelsViewModel(viewModel: LevelsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GameViewModel::class)
    abstract fun bindGameViewModel(viewModel: GameViewModel): ViewModel
}