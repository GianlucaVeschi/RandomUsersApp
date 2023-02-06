package com.gianlucaveschi.randomusersapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.gianlucaveschi.randomusersapp.presentation.ui.theme.RandomUsersAppTheme
import com.gianlucaveschi.randomusersapp.presentation.ui.users.UsersListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getRandomUsers()
        setContent {
            RandomUsersAppTheme {
                val state by viewModel.state.collectAsState()
                UsersListScreen(state)
            }
        }
    }
}
