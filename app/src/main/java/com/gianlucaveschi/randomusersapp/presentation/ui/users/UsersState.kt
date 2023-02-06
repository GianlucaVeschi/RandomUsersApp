package com.gianlucaveschi.randomusersapp.presentation.ui.users

import com.gianlucaveschi.randomusersapp.domain.model.Users

data class UsersState(
    val data: Users? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)