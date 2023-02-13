package com.gianlucaveschi.randomusersapp.data.remote

import com.gianlucaveschi.randomusersapp.data.remote.users.UsersApiResponse
import timber.log.Timber
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: RandomUsersService
) {
    suspend fun getDataFromNetwork(): UsersApiResponse? {
        Timber.d("Trying to get users from the Network...")
        return api.getUsers(page = 1, seed = "abc", results = 20)
            .body()
            .also { Timber.d("Network data retrieval succeded :)") }
    }
}