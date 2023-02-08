package com.gianlucaveschi.randomusersapp.domain.interactors

import com.gianlucaveschi.randomusersapp.domain.mapper.mapToDomain
import com.gianlucaveschi.randomusersapp.domain.model.User
import com.gianlucaveschi.randomusersapp.domain.repo.RandomUsersRepository
import com.gianlucaveschi.randomusersapp.domain.util.Resource
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val usersRepository: RandomUsersRepository
) {

    suspend operator fun invoke(): Resource<List<User>> = try {
        val users = usersRepository.getUsers()
        Resource.Success(data = users.data?.mapToDomain())
    } catch (e: Error) {
        e.printStackTrace()
        Resource.Error(e.message ?: "An unknown error occurred.")
    }
}