package com.example.erc_project.view

import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface BaseViewModelOwner: LifecycleOwner {

    fun refreshProgressDialog(isLoading: Boolean)
    fun showToast(msg: String)

    fun registerBaseViewModel(baseViewModel: BaseViewModel) {
        baseViewModel.isLoading.observe(this, Observer {
            refreshProgressDialog(isLoading = it)
        })

        baseViewModel.toastContent.observe(this, Observer {
            showToast(msg = it)
        })
    }

    fun unregisterBaseViewModel(baseViewModel: BaseViewModel) {
        baseViewModel.isLoading.removeObservers(this)
        baseViewModel.toastContent.removeObservers(this)
    }
}