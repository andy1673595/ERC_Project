package com.example.erc_project.mdoel.data

import com.google.gson.annotations.SerializedName

data class CollectionDetail(
    @SerializedName( "name")
    val name: String = "",

    @SerializedName( "image_url")
    val imgUrl: String = "",

    @SerializedName( "description")
    val description: String = "",

    @SerializedName( "permalink")
    val permalink: String = ""
)