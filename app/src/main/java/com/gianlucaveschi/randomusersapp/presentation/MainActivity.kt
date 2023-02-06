package com.gianlucaveschi.randomusersapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Text
import com.gianlucaveschi.randomusersapp.presentation.ui.theme.RandomUsersAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getRandomUsers()
        setContent {
            RandomUsersAppTheme {
                Text("Gianluca")

            }
        }
    }

}
