package com.zalora.catastrophic.dagger

import com.zalora.catastrophic.dagger.core.CoreComponent
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule


@ModuleScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityBuilder::class]
)
interface AppComponent : ModuleActivityComponent {

    @Component.Builder
    interface Builder {

        fun coreComponent(module: CoreComponent): Builder

        fun build(): AppComponent
    }
}