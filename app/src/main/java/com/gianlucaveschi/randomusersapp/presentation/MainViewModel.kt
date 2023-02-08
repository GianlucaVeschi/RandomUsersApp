package com.gianlucaveschi.randomusersapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gianlucaveschi.randomusersapp.domain.interactors.GetUsersUseCase
import com.gianlucaveschi.randomusersapp.domain.model.User
import com.gianlucaveschi.randomusersapp.domain.util.Resource
import com.gianlucaveschi.randomusersapp.presentation.ui.users.UsersState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UsersState(isLoading = true))
    val state = _state.asStateFlow()

    fun getRandomUsers() {
        viewModelScope.launch {
            setStateToLoading()
            when (val response = getUsersUseCase()) {
                is Resource.Success -> {
                    response.data?.run {
                        onUsersFetched(this)
                    } ?: updateErrorState(
                        errorMessage = "Response was successful but data is null"
                    )
                }
                is Resource.Error -> {
                    updateErrorState(response.message)
                }
            }
        }
    }

    private fun onUsersFetched(users: List<User>) {
        Timber.d("Success $users")
        _state.value = _state.value.copy(
            data = users,
            isLoading = false,
            error = null
        )
    }

    private fun updateErrorState(errorMessage: String?) {
        Timber.d("Error ${errorMessage?: "A generic error has occurred"}")
        _state.value = _state.value.copy(
            data = null,
            isLoading = false,
            error = errorMessage ?: "A generic error has occurred"
        )
    }

    private fun setStateToLoading() {
        Timber.d("Loading...")
        _state.value = _state.value.copy(
            isLoading = true,
            error = null
        )
    }
}