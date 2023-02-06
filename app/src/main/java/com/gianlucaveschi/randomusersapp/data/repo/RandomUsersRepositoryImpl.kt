package com.gianlucaveschi.randomusersapp.data.repo

import com.gianlucaveschi.randomusersapp.data.model.RandomUsersApiResponse
import com.gianlucaveschi.randomusersapp.data.remote.RandomUsersService
import com.gianlucaveschi.randomusersapp.domain.repo.RandomUsersRepository
import com.gianlucaveschi.randomusersapp.domain.util.Resource
import javax.inject.Inject

class RandomUsersRepositoryImpl @Inject constructor(
    private val api: RandomUsersService
) : RandomUsersRepository {

    override suspend fun getRandomUsers(): Resource<RandomUsersApiResponse> = try {
        Resource.Success(
            data = api.getRandomUsers() //.toDomain()
        )
    } catch (e: Exception) {
        e.printStackTrace()
        Resource.Error(e.message ?: "An unknown error occurred.")
    }
}