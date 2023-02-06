package com.gianlucaveschi.randomusersapp.data.users

data class UserApiModel(
    val cell: String,
    val dob: DateOfBirthApiModel,
    val email: String,
    val gender: String,
    val id: IdApiModel,
    val location: LocationApiModel,
    val login: LoginApiModel?,
    val name: NameApiModel,
    val nat: String,
    val phone: String,
    val picture: PictureApiModel,
    val registered: RegisteredApiModel
)