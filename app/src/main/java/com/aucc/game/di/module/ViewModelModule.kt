package com.aucc.game.di.module

import android.arch.lifecycle.ViewModelProvider
import com.aucc.game.di.util.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}