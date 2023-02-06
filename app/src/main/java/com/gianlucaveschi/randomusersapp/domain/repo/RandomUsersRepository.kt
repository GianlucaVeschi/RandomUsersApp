package com.gianlucaveschi.randomusersapp.domain.repo

import com.gianlucaveschi.randomusersapp.domain.model.Users
import com.gianlucaveschi.randomusersapp.domain.util.Resource

interface RandomUsersRepository {
    suspend fun getUsers(): Resource<Users>
}