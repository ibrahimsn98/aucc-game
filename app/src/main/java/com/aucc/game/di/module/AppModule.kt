package com.aucc.game.di.module

import android.app.Application
import android.content.Context
import com.aucc.game.data.database.AppDatabase
import com.aucc.game.data.level.LevelDao
import com.aucc.game.util.PrefUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [(ViewModelModule::class)])
class AppModule {

    @Provides
    @Singleton
    internal fun provideFirestore(): FirebaseFirestore {
        val db = FirebaseFirestore.getInstance()
        db.firestoreSettings = FirebaseFirestoreSettings.Builder().setTimestampsInSnapshotsEnabled(true)
            .setPersistenceEnabled(true).build()
        return db
    }

    @Provides
    @Singleton
    internal fun provideRoomDatabase(app: Application): AppDatabase {
        return AppDatabase.getAppDatabase(app)
    }

    @Provides
    @Singleton
    internal fun provideLevelDao(appDatabase: AppDatabase): LevelDao {
        return appDatabase.levelDao()
    }

    @Provides
    @Singleton
    internal fun provideGlide(context: Context): RequestManager {
        return Glide.with(context)
    }

    @Provides
    fun providePrefUtils(context: Context): PrefUtils {
        return PrefUtils(context)
    }
}