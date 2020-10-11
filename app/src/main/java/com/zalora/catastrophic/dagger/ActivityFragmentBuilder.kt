package com.zalora.catastrophic.dagger

import com.zalora.catastrophic.dagger.BaseActivityFragmentBuilder
import com.zalora.catastrophic.dagger.FragmentBuilderModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityFragmentBuilder : BaseActivityFragmentBuilder() {

//    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
//    internal abstract fun provideHomeFragment(): NewsListFragment

}