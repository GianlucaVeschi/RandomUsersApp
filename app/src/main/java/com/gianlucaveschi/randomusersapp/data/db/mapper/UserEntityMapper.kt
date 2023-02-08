package com.gianlucaveschi.randomusersapp.data.db.mapper

import com.gianlucaveschi.randomusersapp.data.db.users.UserEntity
import com.gianlucaveschi.randomusersapp.data.user.IdDataModel
import com.gianlucaveschi.randomusersapp.data.user.NameDataModel
import com.gianlucaveschi.randomusersapp.data.user.PictureDataModel
import com.gianlucaveschi.randomusersapp.data.user.UserDataModel

fun List<UserEntity>.mapToDataModel(): List<UserDataModel> {
    return this.map {
        it.mapToDataModel()
    }
}

fun List<UserDataModel>.mapToEntityList(): List<UserEntity> {
    return this.map {
        it.mapToEntity()
    }
}

fun UserDataModel.mapToEntity(): UserEntity = UserEntity(
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

fun UserEntity.mapToDataModel() = UserDataModel(
    email = this.email,
    gender = this.gender,
    id = IdDataModel(
        name = this.id,
        value = this.idValue,
    ),
    name = NameDataModel(
        first = this.firstName,
        last = this.lastName,
        title = this.titleName,
    ),
    picture = PictureDataModel(
        large = this.largePicture,
        medium = this.mediumPicture,
        thumbnail = this.thumbnailPicture
    )
)