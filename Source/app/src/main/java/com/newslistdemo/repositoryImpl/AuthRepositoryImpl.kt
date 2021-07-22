package com.newslistdemo.repositoryImpl

import com.newslistdemo.data.dataSource.DataSource
import com.newslistdemo.repository.AuthRepository
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response

class AuthRepositoryImpl(private val dataSource: DataSource) :
    AuthRepository {
    //Providing Implementation of methods for AuthRepository class and call api methods

    override fun getNewsList(apiKey: String, country: String): Single<Response<ResponseBody>> =
        dataSource.restService().getNewsList(apiKey, country)


}