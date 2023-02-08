package com.gianlucaveschi.randomusersapp.data.repo

import com.gianlucaveschi.randomusersapp.data.db.UsersDao
import com.gianlucaveschi.randomusersapp.data.db.mapper.mapToDataModel
import com.gianlucaveschi.randomusersapp.data.db.mapper.mapToEntityList
import com.gianlucaveschi.randomusersapp.data.remote.RandomUsersService
import com.gianlucaveschi.randomusersapp.data.remote.mapper.mapToData
import com.gianlucaveschi.randomusersapp.data.user.UserDataModel
import com.gianlucaveschi.randomusersapp.domain.repo.RandomUsersRepository
import timber.log.Timber
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val api: RandomUsersService,
    private val dao: UsersDao
) : RandomUsersRepository {

    override suspend fun getUsers(): List<UserDataModel> {
        val cachedData = getDataFromCache()
        return cachedData.ifEmpty {
            val networkData = getDataFromNetwork()
            saveNetworkDataLocally(networkData)
            networkData
        }
    }

    private suspend fun getDataFromNetwork(): List<UserDataModel> {
        Timber.d("Trying to get users from the Network...")
        return api.getUsers(page = 1, seed = "abc", results = 20)
            .body()
            ?.mapToData().also { Timber.d("Network data retrieval succeded :)") }
            ?: listOf<UserDataModel>()
    }

    private suspend fun saveNetworkDataLocally(
        users: List<UserDataModel>?
    ) {
        users.takeIf { !it.isNullOrEmpty() }?.let {
            Timber.d("Saving data locally...")
            dao.insertUsers(it.mapToEntityList())
            Timber.d("Saving data locally succeded :)")
        }
    }

    private suspend fun getDataFromCache(): List<UserDataModel> {
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