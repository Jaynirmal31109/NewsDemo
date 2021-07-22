package com.newslistdemo.di.module

import androidx.lifecycle.ViewModel
import com.newslistdemo.di.BaseViewModelFactoryModule
import com.newslistdemo.di.scope.ViewModelKey
import com.newslistdemo.ui.viewModel.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule : BaseViewModelFactoryModule() {

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun newsViewModel(mainViewModel: NewsViewModel): ViewModel

}
