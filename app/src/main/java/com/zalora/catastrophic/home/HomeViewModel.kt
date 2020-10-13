package com.zalora.catastrophic.home

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.byju.news.state.AssistedSavedStateViewModelFactory
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import com.zalora.catastrophic.common.BaseViewModel
import com.zalora.catastrophic.home.room.Cat
import com.zalora.catastrophic.repository.CatRepositoryImpl
import kotlinx.coroutines.flow.Flow

class HomeViewModel
@AssistedInject constructor(
    private val catRestRepo: CatRepositoryImpl,
    @Assisted private val savedStateHandle: SavedStateHandle,
    @Assisted private val bundle: Bundle?
) : BaseViewModel() {

    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<HomeViewModel> {
        override fun create(savedStateHandle: SavedStateHandle, bundle: Bundle?): HomeViewModel
    }

    @ExperimentalPagingApi
    fun fetchCatImages(): LiveData<PagingData<Cat>> {
        return catRestRepo.letCatImagesLiveDb().cachedIn(viewModelScope)
    }
}
