package com.gianlucaveschi.randomusersapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gianlucaveschi.randomusersapp.data.db.users.UserEntity

@Dao
interface UsersDao {

    @Insert
    suspend fun insertUser(user: UserEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUsers(users: List<UserEntity>): LongArray

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<UserEntity>

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUserById(id: String): UserEntity?

    @Query("DELETE FROM users WHERE id IN (:ids)")
    suspend fun deleteUsers(ids: List<String>): Int

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()

    @Query("DELETE FROM users WHERE id = :primaryKey")
    suspend fun deleteUser(primaryKey: String): Int

}