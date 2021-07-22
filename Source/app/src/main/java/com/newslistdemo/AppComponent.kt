package com.newslistdemo

import com.newslistdemo.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        ActivityBindingModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        AppModule::class,
        AppUtilModule::class]

)
interface AppComponent : AndroidInjector<MyApplication> {

    override fun inject(instance: MyApplication?)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(appController: MyApplication): Builder

        fun build(): AppComponent
    }
}