package com.gianlucaveschi.randomusersapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gianlucaveschi.randomusersapp.domain.repo.RandomUsersRepository
import com.gianlucaveschi.randomusersapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val randomUsersRepository: RandomUsersRepository
) : ViewModel() {

    fun getRandomUsers() {
        viewModelScope.launch {
            when (val response = randomUsersRepository.getRandomUsers()) {
                is Resource.Success -> {
                    Timber.d("Success ${response.data}")
                }
                is Resource.Error -> {
                    Timber.d("Error ${response.message}")
                }
            }
        }
    }
}