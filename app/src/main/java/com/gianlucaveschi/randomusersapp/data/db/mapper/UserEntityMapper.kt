package com.gianlucaveschi.randomusersapp.data.db.mapper

import com.gianlucaveschi.randomusersapp.data.db.users.UserEntity
import com.gianlucaveschi.randomusersapp.domain.model.Id
import com.gianlucaveschi.randomusersapp.domain.model.Name
import com.gianlucaveschi.randomusersapp.domain.model.Picture
import com.gianlucaveschi.randomusersapp.domain.model.User

fun List<UserEntity>.toDomain(): List<User> {
    return this.map {
        it.toDomain()
    }
}

fun List<User>.mapToEntityList(): List<UserEntity> {
    return this.map {
        it.mapFromDomain()
    }
}

fun User.mapFromDomain() : UserEntity = UserEntity(
    email = this.email,
    id = this.id.name,
    idValue = this.id.value,
    gender = this.gender,
    mediumPicture = this.picture.medium,
    largePicture = this.picture.large,
    thumbnailPicture = this.picture.thumbnail,
    firstName = this.name.first,
    lastName = this.name.last,
    titleName = this.name.title,
)

fun UserEntity.toDomain() = User(
    email = this.email,
    gender = this.gender,
    id = Id(
        name = this.id,
        value = this.idValue,
    ),
    name = Name(
        first = this.firstName,
        last = this.lastName,
        title = this.titleName,
    ),
    picture = Picture(
        large = this.largePicture,
        medium = this.mediumPicture,
        thumbnail = this.thumbnailPicture
    )
)