package com.gianlucaveschi.randomusersapp.presentation.ui.users

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun UsersList(state: UsersState) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            state.data?.users?.forEach {
                Text(it.name.first)
                Text(it.name.last)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
        state.error?.let { error ->
            Text(
                text = error,
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}