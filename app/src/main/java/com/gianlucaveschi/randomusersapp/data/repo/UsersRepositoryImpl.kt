package com.gianlucaveschi.randomusersapp.data.repo

import com.gianlucaveschi.randomusersapp.data.db.UsersDao
import com.gianlucaveschi.randomusersapp.data.db.mapper.mapToDataModel
import com.gianlucaveschi.randomusersapp.data.db.mapper.mapToEntityList
import com.gianlucaveschi.randomusersapp.data.remote.RandomUsersService
import com.gianlucaveschi.randomusersapp.data.remote.mapper.mapToData
import com.gianlucaveschi.randomusersapp.data.user.UserDataModel
import com.gianlucaveschi.randomusersapp.domain.repo.RandomUsersRepository
import com.gianlucaveschi.randomusersapp.domain.util.Resource
import timber.log.Timber
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val api: RandomUsersService,
    private val dao: UsersDao
) : RandomUsersRepository {

    override suspend fun getUsers(): Resource<List<UserDataModel>> = try {
        val cachedData = getDataFromCache()

        //If Data is not in the cache then get it from the network
        // and save it locally
        if (cachedData.data.isNullOrEmpty()) {
            val networkData = getDataFromNetwork()
            if (networkData is Resource.Success) {
                saveNetworkDataLocally(networkData.data)
            }
            networkData
        } else {
            Resource.Success(data = cachedData.data)
        }
    } catch (e: Error) {
        e.printStackTrace()
        Resource.Error(e.message ?: "An unknown error occurred.")
    }

    private suspend fun getDataFromNetwork(): Resource<List<UserDataModel>> = try {
        Timber.d("Trying to get users from the Network...")

        val dataFromNetwork = api
            .getUsers(page = 1, seed = "abc", results = 20)

        dataFromNetwork.let {
            if (it.isSuccessful && it.body() != null) {
                Timber.d("Network data retrieval succeded :)")
                Resource.Success(data = dataFromNetwork.body()!!.mapToData())
            } else {
                Resource.Error("Network data retrieval failed :(")
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
        Timber.d("Network data retrieval failed :(")
        Resource.Error(e.message ?: "An unknown error occurred.")
    }

    private suspend fun saveNetworkDataLocally(
        users: List<UserDataModel>?
    ): Resource<List<UserDataModel>> = try {
        users.takeIf { !it.isNullOrEmpty() }?.let {
            Timber.d("Saving data locally...")
            dao.insertUsers(it.mapToEntityList())
            Timber.d("Saving data locally succeded :)")
            Resource.Success(it)
        } ?: handleError("Saving data locally failed :(")
    } catch (exception: Exception) {
        handleError(exception.message)
    }

    private suspend fun getDataFromCache(): Resource<List<UserDataModel>> = try {
        Timber.d("Trying to get users from the Cache...")
        val dataFromCache = dao.getAllUsers()
        if (dataFromCache.isNotEmpty()) {
            Timber.d("Cache data retrieval succeeded :)")
            Resource.Success(data = dataFromCache.mapToDataModel())
        } else {
            Timber.d("Cache data retrieval failed :(")
            Resource.Error("Couldn't retrieve data from cache")
        }
    } catch (exception: Exception) {
        Timber.d("Cache data retrieval failed :(")
        handleError(exception.message)
    }

    private fun handleError(exceptionMessage: String?): Resource<List<UserDataModel>> {
        Timber.d(exceptionMessage)
        return Resource.Error(exceptionMessage ?: "Unknown Error")
    }
}