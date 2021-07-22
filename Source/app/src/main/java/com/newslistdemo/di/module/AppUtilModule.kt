package com.newslistdemo.di.module

import android.content.Context
import com.newslistdemo.data.dataSource.DataSource
import com.newslistdemo.data.dataSource.DataSourceImpl
import com.newslistdemo.retrofit.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppUtilModule {

    //It is providing instance of data source class
    @Singleton //To provide single instance
    @Provides //It is providing instance
    fun provideDataSource(context: Context, repoService: ApiService): DataSource =
        DataSourceImpl(context, repoService)

}