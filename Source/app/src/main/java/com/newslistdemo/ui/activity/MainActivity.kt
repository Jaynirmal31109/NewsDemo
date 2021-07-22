package com.newslistdemo.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.newslistdemo.R
import com.newslistdemo.databinding.ActivityMainBinding
import com.newslistdemo.ui.adapter.NewsAdapter
import com.newslistdemo.ui.model.BaseResponse
import com.newslistdemo.ui.viewModel.NewsViewModel
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    private var viewManager = LinearLayoutManager(this)
    private lateinit var viewModel: NewsViewModel
    private var newsAdapter: NewsAdapter? = null
    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var baseResponseData: BaseResponse? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        AndroidInjection.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(NewsViewModel::class.java)

        initialiseAdapter()
    }

    private fun initialiseAdapter() {
        binding.recyclerNews.layoutManager = viewManager
        binding.recyclerNews.apply {
            newsAdapter = NewsAdapter(mutableListOf())
            binding.recyclerNews.adapter = newsAdapter
            binding.recyclerNews.itemAnimator = null
        }
        observeData()
    }

    fun observeData() {
        binding.progress.visibility = View.VISIBLE
        viewModel.getNewsList()

        viewModel.event1.observe(this) {
            when (it.getContentIfNotPending()?.first) {

                "SUCCESS" -> {
                    if (it.getContent().second is BaseResponse) {
                        baseResponseData = it.getContent().second as BaseResponse?
                        if (baseResponseData?.status == "ok") {
                            bindData(baseResponseData)
                        }
                    }
                }
                "FAIL"->{
                    binding.progress.visibility = View.GONE
                    Toast.makeText(this,"Something went wrong..",Toast.LENGTH_LONG).show()
                }
            }
        }
    }


    private fun bindData(baseResponseData: BaseResponse?) {
        baseResponseData?.articles?.let {
            binding.progress.visibility = View.GONE
            newsAdapter?.addAll(baseResponseData.articles as MutableList<BaseResponse.Articles>)

            newsAdapter?.notifyDataSetChanged()
        }
    }
}