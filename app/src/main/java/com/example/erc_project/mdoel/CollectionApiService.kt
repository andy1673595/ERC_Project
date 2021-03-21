package com.example.erc_project.mdoel

import com.example.erc_project.Constant
import com.example.erc_project.mdoel.data.CollectionAssets
import com.example.erc_project.mdoel.data.CollectionDetail
import com.example.erc_project.mdoel.data.CollectionItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CollectionApiService {

    @GET("/assets?format=json&owner=${Constant.OWNER_ADDRESS}")
    suspend fun getCollectionList(
        @Query("offset") since: Int,
        @Query("limit") perPage: Int
    ): Response<CollectionAssets>


    @GET("/asset/{contract_address}/{token_id}")
    suspend fun getCollectionDetail(
        @Path("contract_address") address: String,
        @Path("token_id") tokenId: String
    ): Response<CollectionDetail>

}


