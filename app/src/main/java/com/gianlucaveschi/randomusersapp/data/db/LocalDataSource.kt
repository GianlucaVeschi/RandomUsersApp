package com.gianlucaveschi.randomusersapp.data.db

import com.gianlucaveschi.randomusersapp.data.db.mapper.mapToDomain
import com.gianlucaveschi.randomusersapp.data.db.users.UserEntity
import com.gianlucaveschi.randomusersapp.domain.model.User
import timber.log.Timber
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: UsersDao
) {

    suspend fun saveDataLocally(users: List<UserEntity>?) {
        users.takeIf { !it.isNullOrEmpty() }?.let {
            Timber.d("Saving data locally...")
            dao.insertUsers(it)
            Timber.d("Saving data locally succeded :)")
        }
    }

    suspend fun getDataFromCache(): List<User> {
        val dataFromCache = dao.getAllUsers()
        return if (dataFromCache.isNotEmpty()) {
            Timber.d("Cache data retrieval succeeded :)")
            dataFromCache.mapToDomain()
        } else {
            Timber.d("Cache data retrieval failed :(")
            listOf()
        }
    }
}