package com.danielmartinez.recipes.domain

import java.io.Serializable


data class Recipe(
    var Name        : String?           = null,
    var location    : Location?= null,
    var urlImage    : String?           = null,
    var url         : String?           = null,
    var Description : String?           = null,
    var Author      : String?           = null,
    var Ingredients : ArrayList<String> = arrayListOf(),
    var Method      : ArrayList<String> = arrayListOf()
): Serializable

data class Location (
    var lat   : Double   = 0.0,
    var lng   : Double   = 0.0
):Serializable
