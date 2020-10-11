package com.zalora.catastrophic.repository

import androidx.lifecycle.LiveData
import com.zalora.catastrophic.home.CatResponse

interface CatRepo {

    fun getCatList(page: Int, limit: Int, mimeTypes: String, order: String): LiveData<CatResponse>

}