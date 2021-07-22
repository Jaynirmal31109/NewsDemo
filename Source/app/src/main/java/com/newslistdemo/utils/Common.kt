package com.newslistdemo.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

// concert date formate
fun getAbbreviatedFromDateTime(dateTime: String): String? {
    val dateDefaultFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
    val date = dateDefaultFormat.parse(dateTime)
    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm aa")
    //convert date in api format
    val dateOutput = sdf.format(date)
    return dateOutput
}

// network image show
fun ImageView.loadImageWithGlide(url: String?) {
    Glide.with(this)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}