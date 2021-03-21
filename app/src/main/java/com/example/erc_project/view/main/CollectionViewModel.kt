package com.example.erc_project.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.example.erc_project.mdoel.CollectionRepository
import com.example.erc_project.mdoel.data.CollectionDetail
import com.example.erc_project.mdoel.data.CollectionItem
import com.example.erc_project.view.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.koin.core.inject
import retrofit2.HttpException


class CollectionViewModel : BaseViewModel() {
    private val repository: CollectionRepository by inject()

    private val _itemSelected = MutableLiveData<CollectionItem>()
    val itemSelected: LiveData<CollectionItem> = _itemSelected

    private val _collectionDetail = MutableLiveData<CollectionDetail>()
    val collectionDetail: LiveData<CollectionDetail> = _collectionDetail

    fun getCollectionList(): LiveData<PagedList<CollectionItem>> {
        return repository.getCollectionList(viewModelScope, pagingCallback)
    }

    fun selectCollectionItem(item: CollectionItem) {
        _itemSelected.value = item
    }

    fun getCollectionDetail(item: CollectionItem) {
        viewModelScope.launch {
            flow{
                val result = repository.getCollectionDetail(item)
                if (!result.isSuccessful) throw HttpException(result)
                emit(result)
            }.collectWithGeneralHandle {
                _collectionDetail.value = it
            }
        }
    }

}

