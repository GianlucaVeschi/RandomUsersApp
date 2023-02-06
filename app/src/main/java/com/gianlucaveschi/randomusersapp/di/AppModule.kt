package com.gianlucaveschi.randomusersapp.di

import com.gianlucaveschi.randomusersapp.data.NetworkService.BASE_URL
import com.gianlucaveschi.randomusersapp.data.remote.RandomUsersService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWeatherApi(): RandomUsersService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(RandomUsersService::class.java)
    }
}