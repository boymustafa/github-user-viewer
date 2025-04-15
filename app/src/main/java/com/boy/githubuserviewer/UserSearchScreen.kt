package com.boy.githubuserviewer

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import coil.compose.rememberAsyncImagePainter

@Composable
fun UserSearchScreen(viewModel: MainViewModel, onUserClick: (String) -> Unit) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.statusBars.add(WindowInsets.navigationBars).asPaddingValues())
        ) {
            TextField(
                value = viewModel.searchQuery,
                onValueChange = {
                    viewModel.searchQuery = it
                    viewModel.searchUsers()
                },
                placeholder = { Text("Search users") },
                trailingIcon = {
                    if (viewModel.searchQuery.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Clear search",
                            modifier = Modifier.clickable {
                                viewModel.searchQuery = ""
                                viewModel.searchUsers()
                            }
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )

            when {
                viewModel.isLoading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                viewModel.isEmptyResult -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("No results found.")
                    }
                }
                else -> {
                    LazyColumn {
                        items(viewModel.userList) { user ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .clickable { onUserClick(user.login) }
                                    .padding(8.dp)
                                    .fillMaxWidth()
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter(user.avatarUrl),
                                    contentDescription = null,
                                    modifier = Modifier.size(40.dp).clip(CircleShape)
                                )
                                Spacer(Modifier.width(8.dp))
                                Text(user.login, style = MaterialTheme.typography.bodyLarge)
                            }
                        }
                    }
                }
            }
        }

    }
}

