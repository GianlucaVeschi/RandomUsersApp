package com.gianlucaveschi.randomusersapp.data.repo

import com.gianlucaveschi.randomusersapp.data.db.LocalDataSource
import com.gianlucaveschi.randomusersapp.data.db.mapper.mapToDomain
import com.gianlucaveschi.randomusersapp.data.db.mapper.mapToEntityList
import com.gianlucaveschi.randomusersapp.data.remote.RemoteDataSource
import com.gianlucaveschi.randomusersapp.data.remote.mapper.mapToDomain
import com.gianlucaveschi.randomusersapp.domain.model.User
import com.gianlucaveschi.randomusersapp.domain.repo.RandomUsersRepository
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : RandomUsersRepository {

    override suspend fun getUsers(): List<User> {
        val cachedData = localDataSource.getDataFromCache().mapToDomain()
        return cachedData.ifEmpty {
            val networkData = remoteDataSource.getDataFromNetwork()?.mapToDomain()
            localDataSource.saveDataLocally(networkData?.mapToEntityList())
            localDataSource.getDataFromCache().mapToDomain()
        }
    }
}