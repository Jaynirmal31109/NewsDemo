package com.newslistdemo.data.dataSource

import android.content.Context
import com.newslistdemo.data.dataSource.DataSource
import com.newslistdemo.retrofit.ApiService
import javax.inject.Inject

class DataSourceImpl @Inject constructor(context: Context, private val apiService: ApiService) :
    DataSource {
    //return rest api service
    override fun restService(): ApiService = apiService
}