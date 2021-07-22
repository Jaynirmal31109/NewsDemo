package com.newslistdemo.di.module


import com.newslistdemo.ui.activity.MainActivity
import com.newslistdemo.di.scope.ActivityScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
    includes = [ViewModelModule::class]
)
abstract class ActivityBindingModule {

    @ActivityScoped //define scope
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity


}
