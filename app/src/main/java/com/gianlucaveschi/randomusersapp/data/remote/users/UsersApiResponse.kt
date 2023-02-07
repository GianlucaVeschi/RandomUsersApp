package com.gianlucaveschi.randomusersapp.data.remote.users

data class UsersApiResponse(
    val info: ApiResponseInfoApiModel,
    val results: List<UserApiModel>
)