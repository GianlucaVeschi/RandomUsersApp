package com.gianlucaveschi.randomusersapp.data.users

data class UsersApiResponse(
    val info: ApiResponseInfoApiModel,
    val results: List<UserApiModel>
)