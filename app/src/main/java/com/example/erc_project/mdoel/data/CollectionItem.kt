package com.example.erc_project.mdoel.data

import com.google.gson.annotations.SerializedName

data class CollectionItem(
    @SerializedName( "name")
    val name: String = "",

    @SerializedName( "image_url")
    val imgUrl: String = "",

    @SerializedName( "asset_contract")
    val assetContract: AssetContract? = null,

    @SerializedName( "token_id")
    val tokenId: String = ""
)

data class AssetContract(
    @SerializedName( "address")
    val address: String = ""
)

data class CollectionAssets(
    @SerializedName("assets")
    val list: List<CollectionItem> = listOf()
)