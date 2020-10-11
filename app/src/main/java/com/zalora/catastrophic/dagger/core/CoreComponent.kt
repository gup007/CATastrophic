package com.zalora.catastrophic.dagger.core

import android.content.Context
import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.zalora.catastrophic.R
import com.zalora.catastrophic.common.ResourceProvider
import com.zalora.catastrophic.home.room.AppDatabase
import com.zalora.catastrophic.rest.CacheInterceptor
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@CoreScope
@Component(modules = [CoreModule::class])
interface CoreComponent {
    @Component.Builder
    abstract class Builder {

        @BindsInstance
        abstract fun applicationContext(context: Context): Builder

        abstract fun build(): CoreComponent
    }

    val resourceProvider: ResourceProvider
    val retrofit: Retrofit
    val database: AppDatabase
}

@Module
class CoreModule {

    @Provides
    @CoreScope
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Provides
    @CoreScope
    internal fun provideResource(context: Context): ResourceProvider {
        return ResourceProvider(context)
    }

    @Provides
    @CoreScope
    fun provideDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "room.db")
            .build()

    @Provides
    @CoreScope
    fun provideRetrofit(context: Context): Retrofit {
        val retrofitBuilder = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        val cache = Cache(context.cacheDir, (5 * 1024 * 1024).toLong())
        val cacheInterceptor = CacheInterceptor(context)
        val okHttpClient = OkHttpClient.Builder().cache(cache)
            .addNetworkInterceptor(cacheInterceptor).build()
        return retrofitBuilder.baseUrl(context.getString(R.string.base_url))
            .client(okHttpClient).build()
    }
}
