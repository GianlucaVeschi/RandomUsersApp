package com.gianlucaveschi.randomusersapp.data.users

data class LocationApiModel(
    val city: String,
    val coordinates: CoordinatesApiModel,
    val country: String,
    val postcode: String,
    val state: String,
    val street: StreetApiModel,
    val timezone: TimezoneApiModel
)