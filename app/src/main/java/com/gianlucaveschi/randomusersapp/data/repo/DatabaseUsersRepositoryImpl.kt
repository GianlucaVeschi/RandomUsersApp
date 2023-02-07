package com.gianlucaveschi.randomusersapp.data.repo

import com.gianlucaveschi.randomusersapp.data.db.UsersDao
import com.gianlucaveschi.randomusersapp.data.db.mapper.mapToEntityList
import com.gianlucaveschi.randomusersapp.data.db.mapper.toDomain
import com.gianlucaveschi.randomusersapp.data.remote.RandomUsersService
import com.gianlucaveschi.randomusersapp.data.remote.mapper.mapToDomain
import com.gianlucaveschi.randomusersapp.domain.model.User
import com.gianlucaveschi.randomusersapp.domain.model.Users
import com.gianlucaveschi.randomusersapp.domain.repo.RandomUsersRepository
import com.gianlucaveschi.randomusersapp.domain.util.Resource
import timber.log.Timber
import javax.inject.Inject

class DatabaseUsersRepositoryImpl @Inject constructor(
    private val api: RandomUsersService,
    private val dao: UsersDao
) : RandomUsersRepository {

    override suspend fun getUsers(): Resource<Users> = try {
        val cachedData = getDataFromCache()

        //If Data is not in the cache then get it from the network
        // and save it locally
        if (cachedData.data.isNullOrEmpty()) {
            val networkData = getDataFromNetwork()
            saveNetworkDataLocally(networkData.data?.users)
            networkData
        } else {
            Resource.Success(data = Users(cachedData.data))
        }
    } catch (e: Error) {
        e.printStackTrace()
        Resource.Error(e.message ?: "An unknown error occurred.")
    }

    private suspend fun getDataFromNetwork(): Resource<Users> {
        Timber.d("Trying to get users from the Network...")

        val dataFromNetwork = api
            .getUsers(page = 1, seed = "abc", results = 20)
            .mapToDomain()

        return Resource.Success(data = dataFromNetwork)
    }

    private suspend fun saveNetworkDataLocally(users: List<User>?): Resource<List<User>> = try {
        Timber.d("Saving data locally...")
        users.takeIf { !it.isNullOrEmpty() }?.let {
            dao.insertUsers(it.mapToEntityList())
            Resource.Success(it)
        } ?: handleError("inserting data locally failed")
    } catch (exception: Exception) {
        handleError(exception.message)
    }

    private suspend fun getDataFromCache(): Resource<List<User>> = try {
        Timber.d("Trying to get users from the Cache...")
        val dataFromCache = dao.getAllUsers()
        if (dataFromCache.isNotEmpty()) {
            Timber.d("Cache data retrieval succeeded :)")
            Resource.Success(data = dataFromCache.toDomain())
        } else {
            Timber.d("Cache data retrieval failed :(")
            Resource.Error("Couldn't retrieve data from cache")
        }
    } catch (exception: Exception) {
        Timber.d("Cache data retrieval failed :(")
        handleError(exception.message)
    }

    private fun handleError(exceptionMessage: String?): Resource<List<User>> {
        return Resource.Error(exceptionMessage ?: "Unknown Error")
    }
}