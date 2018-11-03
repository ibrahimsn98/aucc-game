package com.aucc.game.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Singleton
@Module
abstract class ContextModule {

    @Binds
    internal abstract fun provideContext(application: Application): Context
}