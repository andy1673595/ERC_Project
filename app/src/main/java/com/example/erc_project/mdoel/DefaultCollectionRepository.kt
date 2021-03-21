package com.example.erc_project.mdoel

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.erc_project.Constant
import com.example.erc_project.mdoel.data.CollectionDetail
import com.example.erc_project.mdoel.data.CollectionItem
import com.example.erc_project.mdoel.paging.CollectionPagingDataSource
import com.example.erc_project.mdoel.paging.CollectionPagingFactory
import com.example.erc_project.mdoel.paging.PagingCallback
import kotlinx.coroutines.CoroutineScope
import retrofit2.Response
import kotlin.math.hypot

class DefaultCollectionRepository(private val apiService: CollectionApiService): CollectionRepository {

    override fun getCollectionList(scope: CoroutineScope,
                                   pagingCallback: PagingCallback): LiveData<PagedList<CollectionItem>> {

        val pagingDataSource = CollectionPagingDataSource(scope, apiService, pagingCallback)
        val factory = CollectionPagingFactory(pagingDataSource)
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(Constant.COUNT_PER_PAGE)
            .build()

        return LivePagedListBuilder(factory, config).build()
    }

    override suspend fun getCollectionDetail(item: CollectionItem): Response<CollectionDetail> {
        return apiService.getCollectionDetail(
            address = item.assetContract?.address ?: "",
            tokenId = item.tokenId)
    }
}