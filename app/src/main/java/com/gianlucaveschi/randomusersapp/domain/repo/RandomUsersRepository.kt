package com.gianlucaveschi.randomusersapp.domain.repo

import com.gianlucaveschi.randomusersapp.data.model.UsersApiResponse
import com.gianlucaveschi.randomusersapp.domain.util.Resource

interface RandomUsersRepository {
    suspend fun getUsers(): Resource<UsersApiResponse>
}