package com.gianlucaveschi.randomusersapp.data.users

data class ApiResponseInfoApiModel(
    val page: Int,
    val results: Int,
    val seed: String,
    val version: String
)