package com.gianlucaveschi.randomusersapp.domain.model

data class User(
    val email: String,
    val gender: String,
    val id: Id,
    val name: Name,
    val picture: Picture,
)
