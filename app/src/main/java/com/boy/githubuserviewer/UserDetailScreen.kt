package com.boy.githubuserviewer

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.material3.Scaffold
import androidx.core.content.ContextCompat.startActivity
import coil.compose.rememberAsyncImagePainter

@Composable
fun UserDetailScreen(viewModel: MainViewModel) {
    val user = viewModel.selectedUser ?: return
    val repos = viewModel.repos
    val context = LocalContext.current

    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // Safe top/bottom padding
                .padding(horizontal = 16.dp)
        ) {
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = rememberAsyncImagePainter(user.avatarUrl),
                        contentDescription = null,
                        modifier = Modifier.size(60.dp).clip(CircleShape)
                    )
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text(user.name ?: "-", fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
                        Text(user.login)
                        Text("Followers: ${user.followers}, Following: ${user.following}")
                    }
                }
                Spacer(Modifier.height(16.dp))
                Text("Repositories", style = MaterialTheme.typography.titleMedium)
            }

            items(repos) { repo ->
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(repo.url))
                        context.startActivity(intent)
                    }
                    .padding(8.dp)
                ) {
                    Text(repo.name, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
                    Text("⭐ ${repo.stars} • ${repo.language ?: "Unknown"}")
                    Text(repo.description ?: "", maxLines = 2, overflow = TextOverflow.Ellipsis)
                }
                Divider()
            }
        }
    }
}

