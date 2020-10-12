package com.zalora.catastrophic.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.zalora.catastrophic.BuildConfig
import com.zalora.catastrophic.dagger.ModuleScope
import com.zalora.catastrophic.home.Cat
import com.zalora.catastrophic.home.CatApi
import com.zalora.catastrophic.home.CatListResponse
import com.zalora.catastrophic.home.CatResponse
import com.zalora.catastrophic.home.room.AppDatabase
import com.zalora.catastrophic.rest.callback.APICallback
import com.zalora.catastrophic.rest.exception.APIException
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Response
import retrofit2.Retrofit
import javax.inject.Inject

@ModuleScope
class CatRepoRestImpl @Inject constructor(
    private var retrofit: Retrofit,
    private var database: AppDatabase
) : CatRepo {

    private val mediator = MediatorLiveData<CatResponse>()
    private val errorLiveData = MutableLiveData<String>()

    override fun getCatList(
        page: Int,
        limit: Int,
        mimeTypes: String,
        order: String
    ): LiveData<CatResponse> {
//        fetchNewsData(page, limit, mimeTypes, order)
        val databaseSource = database.newsDao().findAll()
        mediator.addSource(databaseSource) {
            mediator.postValue(CatResponse.Success(it))
        }
        mediator.addSource(errorLiveData) {
            mediator.postValue(CatResponse.Error(it ?: ""))
        }
        return mediator
    }

    private fun fetchNewsData(page: Int,
                              limit: Int,
                              mimeTypes: String,
                              order: String) {
        retrofit.create(CatApi::class.java).getCatsAsync(page, limit, mimeTypes, order).enqueue(
            object : APICallback<List<Cat>>(){
                override fun onSuccess(response: List<Cat>, rawResponse: Response) {
                    GlobalScope.launch {
                        if (!response.isNullOrEmpty()) {
                            database.newsDao().insertAll(response)
                        }
                    }
                }

                override fun onFailure(e: APIException) {
                    Log.d(CatRepo::class.simpleName, e.message)
                }

                override fun onComplete() {
                }
            }
        )
    }

}