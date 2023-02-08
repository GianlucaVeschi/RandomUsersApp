package com.gianlucaveschi.randomusersapp.domain.mapper

import com.gianlucaveschi.randomusersapp.data.user.IdDataModel
import com.gianlucaveschi.randomusersapp.data.user.NameDataModel
import com.gianlucaveschi.randomusersapp.data.user.PictureDataModel
import com.gianlucaveschi.randomusersapp.data.user.UserDataModel
import com.gianlucaveschi.randomusersapp.domain.model.Id
import com.gianlucaveschi.randomusersapp.domain.model.Name
import com.gianlucaveschi.randomusersapp.domain.model.Picture
import com.gianlucaveschi.randomusersapp.domain.model.User

fun List<UserDataModel>.mapToDomain(): List<User> {
    return this.map { user ->
        user.mapToDomain()
    }
}

fun UserDataModel.mapToDomain() = User(
    email = this.email,
    gender = this.gender,
    id = this.id.mapToDomain(),
    name = this.name.mapToDomain(),
    picture = this.picture.mapToDomain()
)


private fun IdDataModel.mapToDomain() = Id(
    name = this.name,
    value = this.value
)

private fun NameDataModel.mapToDomain() = Name(
    title = this.title,
    first = this.first,
    last = this.last
)

private fun PictureDataModel.mapToDomain() = Picture(
    large = this.large,
    medium = this.medium,
    thumbnail = this.thumbnail
)