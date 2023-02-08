package com.gianlucaveschi.randomusersapp.presentation.ui.users

import com.gianlucaveschi.randomusersapp.domain.model.User

data class UsersState(
    val data: List<User>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)