package com.gianlucaveschi.randomusersapp.data.user

data class UserDataModel(
    val email: String,
    val gender: String,
    val id: IdDataModel,
    val name: NameDataModel,
    val picture: PictureDataModel,
)
