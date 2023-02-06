package com.gianlucaveschi.randomusersapp.data.mapper

import com.gianlucaveschi.randomusersapp.data.users.*
import com.gianlucaveschi.randomusersapp.domain.model.*

fun UsersApiResponse.mapToDomain(): Users {
    return Users(
        users = this.results.map { user ->
            user.mapToDomain()
        }
    )
}

fun UserApiModel.mapToDomain() = User(
    email = this.email,
    gender = this.gender,
    id = this.id.mapToDomain(),
    name = this.name.mapToDomain(),
    picture = this.picture.mapToDomain()
)


fun IdApiModel.mapToDomain() = Id(
    name = this.name,
    value = this.value
)

fun NameApiModel.mapToDomain() = Name(
    title = this.title,
    first = this.first,
    last = this.last
)

fun PictureApiModel.mapToDomain() = Picture(
    large = this.large,
    medium = this.medium,
    thumbnail = this.thumbnail
)


