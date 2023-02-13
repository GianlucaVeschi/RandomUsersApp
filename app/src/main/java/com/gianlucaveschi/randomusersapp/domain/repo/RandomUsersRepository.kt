package com.gianlucaveschi.randomusersapp.domain.repo

import com.gianlucaveschi.randomusersapp.domain.model.User

interface RandomUsersRepository {
    suspend fun getUsers(): List<User>
}