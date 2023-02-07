package com.gianlucaveschi.randomusersapp.data.db.users

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ids")
data class IdEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "name")
    val name: String,

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "value")
    val value: String?
)
