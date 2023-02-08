package com.gianlucaveschi.randomusersapp.domain.repo

import com.gianlucaveschi.randomusersapp.data.user.UserDataModel

interface RandomUsersRepository {
    suspend fun getUsers(): List<UserDataModel>
}