package com.gianlucaveschi.randomusersapp.data.db.users

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "names")
data class NameEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "last")
    val last: String,

    @ColumnInfo(name = "first")
    val first: String,

    @ColumnInfo(name = "title")
    val title: String
)