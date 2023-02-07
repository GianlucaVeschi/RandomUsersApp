package com.gianlucaveschi.randomusersapp.di

import androidx.room.Room
import com.gianlucaveschi.randomusersapp.RandomUsersApp
import com.gianlucaveschi.randomusersapp.data.DatabaseService
import com.gianlucaveschi.randomusersapp.data.db.AppDatabase
import com.gianlucaveschi.randomusersapp.data.db.UsersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideDb(app: RandomUsersApp): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, DatabaseService.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUsersDao(db: AppDatabase): UsersDao {
        return db.usersDao()
    }
}