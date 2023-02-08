package com.gianlucaveschi.randomusersapp.data.remote.mapper

import com.gianlucaveschi.randomusersapp.data.remote.users.*
import com.gianlucaveschi.randomusersapp.data.user.IdDataModel
import com.gianlucaveschi.randomusersapp.data.user.NameDataModel
import com.gianlucaveschi.randomusersapp.data.user.PictureDataModel
import com.gianlucaveschi.randomusersapp.data.user.UserDataModel

fun UsersApiResponse.mapToData(): List<UserDataModel> {
    return this.results.map { user ->
        user.mapToData()
    }
}

fun UserApiModel.mapToData() = UserDataModel(
    email = this.email,
    gender = this.gender,
    id = this.id.mapToData(),
    name = this.name.mapToData(),
    picture = this.picture.mapToData()
)


private fun IdApiModel.mapToData() = IdDataModel(
    name = this.name,
    value = this.value
)

private fun NameApiModel.mapToData() = NameDataModel(
    title = this.title,
    first = this.first,
    last = this.last
)

private fun PictureApiModel.mapToData() = PictureDataModel(
    large = this.large,
    medium = this.medium,
    thumbnail = this.thumbnail
)


