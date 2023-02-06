package com.gianlucaveschi.randomusersapp.data.remote

import com.gianlucaveschi.randomusersapp.data.model.RandomUsersApiResponse
import retrofit2.http.GET

interface RandomUsersService {

    @GET("?exc=login&page=1&results=10&seed=abc")
    suspend fun getRandomUsers(): RandomUsersApiResponse
}