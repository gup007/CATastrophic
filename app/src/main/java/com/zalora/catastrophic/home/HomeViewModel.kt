package com.zalora.catastrophic.home

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.byju.news.state.AssistedSavedStateViewModelFactory
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import com.zalora.catastrophic.common.BaseViewModel
import com.zalora.catastrophic.repository.CatRepoRestImpl
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel @AssistedInject constructor(
    private val newsRestRepo: CatRepoRestImpl,
    @Assisted private val savedStateHandle: SavedStateHandle,
    @Assisted private val bundle: Bundle?
) : BaseViewModel() {

    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<HomeViewModel> {
        override fun create(savedStateHandle: SavedStateHandle, bundle: Bundle?): HomeViewModel
    }

    val newsList: MutableLiveData<List<Cat>> = MutableLiveData()

    fun fetchNewsList(query: String): LiveData<CatResponse> {
        val calender = Calendar.getInstance()
        val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formattedDate: String = df.format(calender.time)
        return newsRestRepo.getCatList(
            query = query,
            date = formattedDate,
            sortBy = "publishedAt"
        )
    }
}
