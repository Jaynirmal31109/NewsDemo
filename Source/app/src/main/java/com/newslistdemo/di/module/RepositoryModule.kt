package com.newslistdemo.di.module

import com.newslistdemo.data.dataSource.DataSource
import com.newslistdemo.repository.AuthRepository
import com.newslistdemo.repositoryImpl.AuthRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides //It will provide instance of AuthRepository
    fun provideAuthRepo(dataSource: DataSource): AuthRepository =
        AuthRepositoryImpl(dataSource)

}