package com.newslistdemo.repository

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response


interface AuthRepository {

    //abstract news method for api call
    fun getNewsList(apiKey:String,country:String): Single<Response<ResponseBody>>
}