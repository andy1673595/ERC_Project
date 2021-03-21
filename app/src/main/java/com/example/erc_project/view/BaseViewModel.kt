package com.example.erc_project.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.erc_project.mdoel.paging.PagingCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import org.koin.core.KoinComponent
import retrofit2.Response

abstract class BaseViewModel: ViewModel(), KoinComponent {
    val isLoading = MutableLiveData<Boolean>().also { it.value = false }
    val toastContent = MutableLiveData<String>()

    protected val pagingCallback = object : PagingCallback {
        override fun onLoading() {
            isLoading.value = true
        }

        override fun onLoaded() {
            isLoading.value = false
        }

        override fun onThrowable(throwable: Throwable) {
            toastContent.value = throwable.message
        }
    }

    @ExperimentalCoroutinesApi
    suspend fun <T> Flow<Response<T>>.collectWithGeneralHandle(action: (value: T) -> Unit) {
        this.flowOn(Dispatchers.IO)
                .onStart {
                    isLoading.value = true
                }
                .catch { e -> toastContent.value = e.message }
                .onCompletion {
                    isLoading.value = false
                }
                .collect { response ->
                    if(response.isSuccessful) {
                        response.body()?.run(action)
                    }
                }
    }
}