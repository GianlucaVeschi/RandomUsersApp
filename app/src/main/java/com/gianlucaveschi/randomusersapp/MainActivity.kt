package com.gianlucaveschi.randomusersapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import com.gianlucaveschi.randomusersapp.presentation.ui.theme.RandomUsersAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomUsersAppTheme {
                Text("Gianluca")
            }
        }
    }

}
