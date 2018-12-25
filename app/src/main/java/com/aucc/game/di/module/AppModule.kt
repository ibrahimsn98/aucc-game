package com.aucc.game.di.module

import android.content.Context
import com.aucc.game.rest.RestService
import com.aucc.game.util.Constants
import com.aucc.game.util.NetworkUtils.hasNetwork
import com.aucc.game.util.PrefUtils
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [(ViewModelModule::class)])
class AppModule {

    @Provides
    @Singleton
    fun provideClient(context: Context): OkHttpClient {
        val myCache = Cache(context.cacheDir, (10 * 1024 * 1024).toLong())

        return OkHttpClient.Builder()
            .cache(myCache)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (hasNetwork(context)!!)
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitService(retrofit: Retrofit): RestService {
        return retrofit.create<RestService>(RestService::class.java)
    }

    @Provides
    fun providePrefUtils(context: Context): PrefUtils {
        return PrefUtils(context)
    }
}