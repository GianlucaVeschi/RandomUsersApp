package com.gianlucaveschi.randomusersapp.data.remote

import com.gianlucaveschi.randomusersapp.data.remote.mapper.mapToDomain
import com.gianlucaveschi.randomusersapp.domain.model.User
import timber.log.Timber
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: RandomUsersService
) {
    suspend fun getDataFromNetwork(): List<User>? {
        Timber.d("Trying to get users from the Network...")
        return api.getUsers(page = 1, seed = "abc", results = 20)
            .body()
            ?.mapToDomain()
            .also { Timber.d("Network data retrieval succeded :)") }
    }
}