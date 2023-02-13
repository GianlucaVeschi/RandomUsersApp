package com.gianlucaveschi.randomusersapp.data.remote.mapper

import com.gianlucaveschi.randomusersapp.data.remote.users.*
import com.gianlucaveschi.randomusersapp.domain.model.Id
import com.gianlucaveschi.randomusersapp.domain.model.Name
import com.gianlucaveschi.randomusersapp.domain.model.Picture
import com.gianlucaveschi.randomusersapp.domain.model.User

fun UsersApiResponse.mapToDomain(): List<User> {
    return this.results.map { user ->
        user.mapToDomain()
    }
}

fun UserApiModel.mapToDomain() = User(
    email = this.email,
    gender = this.gender,
    id = this.id.mapToDomain(),
    name = this.name.mapToDomain(),
    picture = this.picture.mapToDomain()
)


private fun IdApiModel.mapToDomain() = Id(
    name = this.name,
    value = this.value
)

private fun NameApiModel.mapToDomain() = Name(
    title = this.title,
    first = this.first,
    last = this.last
)

private fun PictureApiModel.mapToDomain() = Picture(
    large = this.large,
    medium = this.medium,
    thumbnail = this.thumbnail
)


