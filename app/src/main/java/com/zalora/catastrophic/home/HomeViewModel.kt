package com.zalora.catastrophic.home

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.byju.news.state.AssistedSavedStateViewModelFactory
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import com.zalora.catastrophic.common.BaseViewModel
import com.zalora.catastrophic.repository.CatRepo
import com.zalora.catastrophic.repository.CatRepoRestImpl

class HomeViewModel @AssistedInject constructor(
    private val catRestRepo: CatRepoRestImpl,
    @Assisted private val savedStateHandle: SavedStateHandle,
    @Assisted private val bundle: Bundle?
) : BaseViewModel() {

    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<HomeViewModel> {
        override fun create(savedStateHandle: SavedStateHandle, bundle: Bundle?): HomeViewModel
    }

    val catList: MutableLiveData<List<Cat>> = MutableLiveData()

    fun fetchNewsList(): LiveData<CatResponse> {
        return catRestRepo.getCatList(
            1, 20, "png", "Desc"
        )
    }
}
