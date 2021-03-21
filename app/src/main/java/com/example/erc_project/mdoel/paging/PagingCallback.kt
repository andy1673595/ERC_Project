package com.example.erc_project.mdoel.paging

interface PagingCallback {
    fun onLoading()
    fun onLoaded()
    fun onThrowable(throwable: Throwable)
}