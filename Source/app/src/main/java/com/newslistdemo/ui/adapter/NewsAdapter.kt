package com.newslistdemo.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.newslistdemo.databinding.RowNewsBinding
import com.newslistdemo.ui.model.BaseResponse
import com.newslistdemo.utils.getAbbreviatedFromDateTime
import com.newslistdemo.utils.loadImageWithGlide

class NewsAdapter(private var arcticalList: MutableList<BaseResponse.Articles>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder?
        val inflater = LayoutInflater.from(parent.context)
        val viewItem = RowNewsBinding.inflate(inflater, parent, false)
        return MyLeaveViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val artical: BaseResponse.Articles = arcticalList[position]

        val myLeaveViewHolder: MyLeaveViewHolder =
            holder as MyLeaveViewHolder
        myLeaveViewHolder.bind(artical)

    }

    inner class MyLeaveViewHolder(private val viewBinding: RowNewsBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(
            data: BaseResponse.Articles,
        ) {
            viewBinding.textPublished.text = data.publishedAt?.let { getAbbreviatedFromDateTime(it) }
            viewBinding.imgNews.loadImageWithGlide(data.urlToImage)
            viewBinding.articles = data
            viewBinding.executePendingBindings()
        }
    }


    override fun getItemCount(): Int = if (arcticalList.isEmpty()) 0 else arcticalList.size

    fun addAll(myLeaveList: MutableList<BaseResponse.Articles>) {
        for (result in myLeaveList) {
            add(result)
        }
    }


    fun add(employeeLeave: BaseResponse.Articles?) {
        employeeLeave?.let { arcticalList.add(it) }
        notifyItemInserted(arcticalList.size - 1)
    }

}