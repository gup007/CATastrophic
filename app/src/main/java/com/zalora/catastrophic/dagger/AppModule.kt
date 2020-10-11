package com.zalora.catastrophic.dagger

import android.app.Application
import android.content.Context
import com.zalora.catastrophic.dagger.CommonUiModule
import com.zalora.catastrophic.dagger.ModuleScope
import com.zalora.catastrophic.dagger.ViewModelModule
import dagger.Module
import dagger.Provides

@Module(includes = [CommonUiModule::class, ViewModelModule::class])
class AppModule {

    @ModuleScope
    @Provides
    internal fun provideApplicationContext(application: Application): Context {
        return application.applicationContext
    }
}
