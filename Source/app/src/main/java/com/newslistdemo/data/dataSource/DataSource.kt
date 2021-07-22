package com.newslistdemo.data.dataSource

import com.newslistdemo.retrofit.ApiService


interface DataSource {
    //abstract function rest api service
    fun restService(): ApiService
}