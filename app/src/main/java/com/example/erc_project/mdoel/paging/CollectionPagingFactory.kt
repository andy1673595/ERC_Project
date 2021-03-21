package com.example.erc_project.mdoel.paging

import androidx.paging.DataSource
import com.example.erc_project.mdoel.data.CollectionItem

class CollectionPagingFactory(private val pagingDataSource: DataSource<Int, CollectionItem>) :
    DataSource.Factory<Int, CollectionItem>() {

    override fun create(): DataSource<Int, CollectionItem> {
        return pagingDataSource
    }
}