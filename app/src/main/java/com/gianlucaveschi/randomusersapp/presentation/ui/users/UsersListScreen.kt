package com.gianlucaveschi.randomusersapp.presentation.ui.users

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.gianlucaveschi.randomusersapp.domain.model.User

@Composable
fun UsersListScreen(state: UsersState) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        state.data?.let { users ->
            LazyColumn {
                items(users) { user ->
                    UserCard(user)
                }
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

@Composable
private fun UserCard(user: User) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .size(130.dp)
            .padding(10.dp)
            .wrapContentHeight(align = Alignment.Top),
        shape = CutCornerShape(topEnd = 20.dp),
        elevation = 8.dp,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .size(80.dp)
                    .clip(CircleShape)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(user.picture.thumbnail),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )
            }
            Spacer(modifier = Modifier.size(size = 16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp)
            ) {
                Text(text = "${user.name.first} ${user.name.last}")
                Spacer(modifier = Modifier.size(size = 8.dp))
                Text(text = user.email)
            }
        }
    }
}