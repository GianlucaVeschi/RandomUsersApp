package com.gianlucaveschi.randomusersapp.domain.interactors

import com.gianlucaveschi.randomusersapp.domain.model.User
import com.gianlucaveschi.randomusersapp.domain.repo.RandomUsersRepository
import com.gianlucaveschi.randomusersapp.domain.util.Resource
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val usersRepository: RandomUsersRepository
) {

    suspend operator fun invoke(): Resource<List<User>> = try {
        val users = usersRepository.getUsers()
        if (users.isNotEmpty()) {
            Resource.Success(data = users)
        } else {
            Resource.Error("Couldn't retrieve data")
        }
    } catch (e: Exception) {
        e.printStackTrace()
        Resource.Error(e.message ?: "An unknown error occurred.")
    }
}