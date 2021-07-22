package com.newslistdemo.di

import androidx.lifecycle.ViewModelProvider
import com.newslistdemo.di.BaseAppViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class BaseViewModelFactoryModule {

    @Binds //binds BaseAppViewModelFactory
    abstract fun bindViewModelFactory(factory: BaseAppViewModelFactory): ViewModelProvider.Factory
}