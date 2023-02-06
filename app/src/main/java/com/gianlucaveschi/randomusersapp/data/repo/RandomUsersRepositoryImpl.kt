package com.gianlucaveschi.randomusersapp.data.repo

import com.gianlucaveschi.randomusersapp.data.mapper.mapToDomain
import com.gianlucaveschi.randomusersapp.data.remote.RandomUsersService
import com.gianlucaveschi.randomusersapp.domain.model.Users
import com.gianlucaveschi.randomusersapp.domain.repo.RandomUsersRepository
import com.gianlucaveschi.randomusersapp.domain.util.Resource
import javax.inject.Inject

class RandomUsersRepositoryImpl @Inject constructor(
    private val api: RandomUsersService
) : RandomUsersRepository {

    override suspend fun getUsers(): Resource<Users> = try {
        Resource.Success(
            data = api.getUsers(
                page = 1,
                seed = "abc",
                results = 20
            ).mapToDomain()
        )
    } catch (e: Exception) {
        e.printStackTrace()
        Resource.Error(e.message ?: "An unknown error occurred.")
    }
}