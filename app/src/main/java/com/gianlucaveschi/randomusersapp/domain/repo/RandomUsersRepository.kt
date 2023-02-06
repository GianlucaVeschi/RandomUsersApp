package com.gianlucaveschi.randomusersapp.domain.repo

import com.gianlucaveschi.randomusersapp.data.model.RandomUsersApiResponse
import com.gianlucaveschi.randomusersapp.domain.util.Resource

interface RandomUsersRepository {
    suspend fun getRandomUsers(): Resource<RandomUsersApiResponse>
}