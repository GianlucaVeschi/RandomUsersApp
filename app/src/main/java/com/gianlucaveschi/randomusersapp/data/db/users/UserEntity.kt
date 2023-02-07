package com.gianlucaveschi.randomusersapp.data.db.users

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(

    // ID
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "idValue")
    val idValue: String?,

    // Name
    @ColumnInfo(name = "lastName")
    val lastName: String,

    @ColumnInfo(name = "firstName")
    val firstName: String,

    @ColumnInfo(name = "titleName")
    val titleName: String,

    // Picture
    @ColumnInfo(name = "largePicture")
    val largePicture: String,

    @ColumnInfo(name = "mediumPicture")
    val mediumPicture: String,

    @ColumnInfo(name = "thumbnailPicture")
    val thumbnailPicture: String,

    // Standalone fields
    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "gender")
    val gender: String,
)
