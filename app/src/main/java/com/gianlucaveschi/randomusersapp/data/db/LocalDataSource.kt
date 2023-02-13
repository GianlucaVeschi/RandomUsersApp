package com.gianlucaveschi.randomusersapp.data.db

import com.gianlucaveschi.randomusersapp.data.db.mapper.mapToDataModel
import com.gianlucaveschi.randomusersapp.data.db.mapper.mapToEntityList
import com.gianlucaveschi.randomusersapp.data.user.UserDataModel
import timber.log.Timber
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: UsersDao
) {

    suspend fun saveDataLocally(
        users: List<UserDataModel>?
    ) {
        users.takeIf { !it.isNullOrEmpty() }?.let {
            Timber.d("Saving data locally...")
            dao.insertUsers(it.mapToEntityList())
            Timber.d("Saving data locally succeded :)")
        }
    }

    suspend fun getDataFromCache(): List<UserDataModel> {
        val dataFromCache = dao.getAllUsers()
        return if (dataFromCache.isNotEmpty()) {
            Timber.d("Cache data retrieval succeeded :)")
            dataFromCache.mapToDataModel()
        } else {
            Timber.d("Cache data retrieval failed :(")
            listOf()
        }
    }
}