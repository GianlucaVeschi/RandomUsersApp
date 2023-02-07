package com.gianlucaveschi.randomusersapp.data.remote.users

data class ApiResponseInfoApiModel(
    val page: Int,
    val results: Int,
    val seed: String,
    val version: String
)