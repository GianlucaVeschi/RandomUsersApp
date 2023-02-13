package com.gianlucaveschi.randomusersapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gianlucaveschi.randomusersapp.data.db.users.UserEntity

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUsers(users: List<UserEntity>): LongArray

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<UserEntity>

}