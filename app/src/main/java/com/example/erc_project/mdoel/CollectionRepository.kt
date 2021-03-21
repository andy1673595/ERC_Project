package com.example.erc_project.mdoel

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.erc_project.mdoel.data.CollectionDetail
import com.example.erc_project.mdoel.data.CollectionItem
import com.example.erc_project.mdoel.paging.PagingCallback
import kotlinx.coroutines.CoroutineScope
import retrofit2.Response

interface CollectionRepository {
    fun getCollectionList(scope: CoroutineScope, pagingCallback: PagingCallback): LiveData<PagedList<CollectionItem>>

    suspend fun getCollectionDetail(item: CollectionItem): Response<CollectionDetail>
}