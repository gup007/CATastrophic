package com.zalora.catastrophic.dagger


import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module

@AssistedModule
@Module(includes = [AssistedInject_ViewModelModule::class])
internal abstract class ViewModelModule {

//    @Binds
//    @IntoMap
//    @ViewModelKey(HomeViewModel::class)
//    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel
//
//
//    @Binds
//    abstract fun bindViewModelFactory(factory: CustomViewModelFactory): ViewModelProvider.Factory


//    @Binds
//    @IntoMap
//    @ViewModelKey(HomeViewModel::class)
//    abstract fun bindsSomeViewModel(f: HomeViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>
}
