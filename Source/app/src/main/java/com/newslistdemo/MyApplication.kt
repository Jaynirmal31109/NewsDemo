package com.newslistdemo

import com.newslistdemo.data.dataSource.DataSource
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class MyApplication : DaggerApplication() {
    @Inject
    lateinit var dataSource: DataSource

    companion object {
        lateinit var instance: MyApplication
    }

    // To crete instance of app component
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        appComponent.inject(this)
        return appComponent
    }

}