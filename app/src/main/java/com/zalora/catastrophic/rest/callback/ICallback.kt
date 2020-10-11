package com.zalora.catastrophic.rest.callback

import com.zalora.catastrophic.rest.exception.APIException
import retrofit2.Callback

interface ICallback<T> : Callback<T> {


    /**
     * @param response from the rest API
     */
    fun onSuccess(response: T, rawResponse: okhttp3.Response)

    /**
     * @param e response from rest API
     */
    fun onFailure(e: APIException)


    /**
     * Marker method. to be implemented in child class if required
     */
    fun onComplete()

}