package com.zalora.catastrophic.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.zalora.catastrophic.dagger.ModuleScope
import com.zalora.catastrophic.home.room.AppDatabase
import com.zalora.catastrophic.home.room.Cat
import retrofit2.Retrofit
import javax.inject.Inject

@ModuleScope
class CatRepositoryImpl @Inject constructor(
    private var retrofit: Retrofit,
    private var database: AppDatabase
) {

    @ExperimentalPagingApi
    private var mediator = CatMediator(retrofit, database)

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(
            pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true, prefetchDistance = 5,
            initialLoadSize = 40
        )
    }

    @ExperimentalPagingApi
    fun letCatImagesLiveDb(pagingConfig: PagingConfig = getDefaultPageConfig()): LiveData<PagingData<Cat>> {
        val pagingSourceFactory = { database.getCatDao().findAll() }
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = mediator
        ).liveData
    }

    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 20
    }
}