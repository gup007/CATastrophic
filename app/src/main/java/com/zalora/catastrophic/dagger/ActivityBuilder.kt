package com.zalora.catastrophic.dagger

import com.zalora.catastrophic.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [ActivityModule::class, ActivityFragmentBuilder::class])
    internal abstract fun bindHomeActivity(): HomeActivity

}
