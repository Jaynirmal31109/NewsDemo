package com.newslistdemo.retrofit

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET("top-headlines")
    fun getNewsList(@Query("apiKey") apiKey: String,
                        @Query("country") country: String): Single<Response<ResponseBody>>


}