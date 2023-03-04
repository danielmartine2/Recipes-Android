package com.danielmartinez.recipes.framework.data.datasources

import com.danielmartinez.recipes.domain.Location
import com.google.gson.annotations.SerializedName

data class RemoteResult(
    val page: Int?,
    val results: MutableList<ServiceRecipe>,
    @SerializedName("total_pages")   val totalPages: Int?,
    @SerializedName("total_results") val totalResults: Int?
)

data class ServiceRecipe(
    @SerializedName("Name")        var Name        : String?           = null,
    @SerializedName("location") var location       : Location?= null,
    @SerializedName("urlImage")    var urlImage    : String?           = null,
    @SerializedName("url")         var url         : String?           = null,
    @SerializedName("Description") var Description : String?           = null,
    @SerializedName("Author")      var Author      : String?           = null,
    @SerializedName("Ingredients") var Ingredients : ArrayList<String> = arrayListOf(),
    @SerializedName("Method")      var Method      : ArrayList<String> = arrayListOf()
)