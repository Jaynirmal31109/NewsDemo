package com.newslistdemo.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.newslistdemo.data.dataSource.DataSource
import com.newslistdemo.repository.AuthRepository
import com.newslistdemo.ui.model.BaseResponse
import com.newslistdemo.utils.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val dataSource: DataSource,
) : ViewModel() {

    private val _event1 = MutableLiveData<Event<Pair<String, *>>>()
    val event1: LiveData<Event<Pair<String, *>>> = _event1

    fun getNewsList() {
        val disposableObserver = object : DisposableSingleObserver<Response<ResponseBody>>() {
            override fun onSuccess(value: Response<ResponseBody>) {
                when (value.code()) {
                    200 -> {
                        val baseResponse =
                            Gson().fromJson(value.body()?.string(), BaseResponse::class.java)
                        _event1.postValue(Event(Pair("SUCCESS", baseResponse)))
                    }

                    else -> {
                        _event1.postValue(Event(Pair("FAIL", value)))
                    }
                }
            }
            override fun onError(e: Throwable) {
            }
        }

        authRepository.getNewsList("02e04c82496a4e489f0035e2db43fb9a", "IN")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(disposableObserver)

    }
}