package com.zalora.catastrophic.dagger


import androidx.lifecycle.ViewModel
import com.byju.news.state.AssistedSavedStateViewModelFactory
import com.squareup.inject.assisted.dagger2.AssistedModule
import com.zalora.catastrophic.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@AssistedModule
@Module(includes = [AssistedInject_ViewModelModule::class])
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindsSomeViewModel(f: HomeViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>
}
