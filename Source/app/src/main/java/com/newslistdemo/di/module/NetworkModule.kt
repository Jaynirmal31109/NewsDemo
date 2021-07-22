package com.newslistdemo.di.module

import android.content.Context
import com.google.gson.GsonBuilder
import com.newslistdemo.BuildConfig
import com.newslistdemo.retrofit.ApiService
import com.newslistdemo.retrofit.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Modifier
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton //To provide single instance
    @Provides // Providing instance of okhttp client
    fun provideOkHttpClient(
        context: Context
    ): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()

        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
                .writeTimeout(35,TimeUnit.SECONDS)
                .readTimeout(35, TimeUnit.SECONDS)
                .connectTimeout(35, TimeUnit.SECONDS)
                //.callTimeout(120,TimeUnit.SECONDS)
            .addInterceptor(NetworkConnectionInterceptor(context)) //added network interceptor
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides // Providing instance of retrofit
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val builder = GsonBuilder().excludeFieldsWithModifiers(
            Modifier.FINAL,
            Modifier.TRANSIENT,
            Modifier.STATIC
        )
        val gson = builder.create()
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides // Providing instance of ApiService
    @Singleton
    fun providesNetworkService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}