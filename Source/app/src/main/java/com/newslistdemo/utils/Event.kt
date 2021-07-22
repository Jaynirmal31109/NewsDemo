package com.newslistdemo.utils

class Event<T>(private val content: T) {

    private var pending = true

    //get content if not pending in observable
    fun getContentIfNotPending(): T? {
        return if (pending) {
            pending = false
            content
        } else {
            null
        }
    }

    //get content from response
    fun getContent(): T = content
}