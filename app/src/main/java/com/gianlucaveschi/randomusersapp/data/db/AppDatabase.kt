package com.gianlucaveschi.randomusersapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gianlucaveschi.randomusersapp.data.db.users.UserEntity

@Database(entities = [UserEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usersDao(): UsersDao

}