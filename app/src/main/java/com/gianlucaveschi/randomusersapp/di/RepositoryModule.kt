package com.gianlucaveschi.randomusersapp.di

import com.gianlucaveschi.randomusersapp.data.repo.RandomUsersRepositoryImpl
import com.gianlucaveschi.randomusersapp.domain.repo.RandomUsersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        randomUsersRepositoryImpl: RandomUsersRepositoryImpl
    ): RandomUsersRepository
}