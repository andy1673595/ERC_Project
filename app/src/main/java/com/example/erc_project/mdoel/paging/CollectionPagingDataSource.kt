package com.example.erc_project.mdoel.paging

import android.util.Log
import androidx.paging.PositionalDataSource
import com.example.erc_project.Constant
import com.example.erc_project.mdoel.CollectionApiService
import com.example.erc_project.mdoel.data.CollectionAssets
import com.example.erc_project.mdoel.data.CollectionItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response


class CollectionPagingDataSource(
    private val scope: CoroutineScope,
    private val apiService: CollectionApiService,
    private val pagingCallback: PagingCallback

) : PositionalDataSource<CollectionItem>() {

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<CollectionItem>
    ) {
        val position = params.requestedStartPosition

        scope.launch {
            flow {
                val result = apiService.getCollectionList(position, Constant.COUNT_PER_PAGE)
                if (!result.isSuccessful) throw HttpException(result)
                emit(result)

            }.collectionWithGeneralHandle {
                callback.onResult(it.list , position)
            }
        }
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<CollectionItem>) {
        val position = params.startPosition

        scope.launch {
            flow {
                val result = apiService.getCollectionList(position, Constant.COUNT_PER_PAGE)
                if (!result.isSuccessful) throw HttpException(result)
                emit(result)
            }.collectionWithGeneralHandle {
                callback.onResult(it.list)
            }
        }
    }

    private suspend fun Flow<Response<CollectionAssets>>
            .collectionWithGeneralHandle(action: (CollectionAssets) -> Unit) {

        this.flowOn(Dispatchers.IO)
                .onStart { pagingCallback.onLoading() }
                .catch { pagingCallback.onThrowable(it) }
                .onCompletion { pagingCallback.onLoaded() }
                .collect { response ->
                    if(response.isSuccessful) {
                        response.body()?.run(action)
                    }
                }

    }
}
