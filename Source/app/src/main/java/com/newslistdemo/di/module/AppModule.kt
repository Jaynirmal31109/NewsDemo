package com.newslistdemo.di.module

import android.content.Context
import com.newslistdemo.MyApplication
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Singleton //to provide single instance
    @Binds
    abstract fun bindApplication(appController: MyApplication): Context
}