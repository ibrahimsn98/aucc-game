package com.aucc.game.di.component

import android.app.Application
import com.aucc.game.base.BaseApplication
import com.aucc.game.di.module.AppModule
import com.aucc.game.di.module.BindingModule
import com.aucc.game.di.module.ContextModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = ([ContextModule::class, AndroidSupportInjectionModule::class, AppModule::class, BindingModule::class]))
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(application: BaseApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}