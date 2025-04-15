package com.boy.githubuserviewer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val api: GitHubService) : ViewModel() {
    var searchQuery by mutableStateOf("")
    var userList by mutableStateOf<List<GitHubUserBrief>>(emptyList())
    var selectedUser by mutableStateOf<GitHubUser?>(null)
    var repos by mutableStateOf<List<GitHubRepo>>(emptyList())

    fun searchUsers() {
        viewModelScope.launch {
            try { userList = api.searchUsers(searchQuery).items } catch (e: Exception) { e.printStackTrace() }
        }
    }

    fun loadUserDetail(username: String) {
        viewModelScope.launch {
            try {
                selectedUser = api.getUserDetail(username)
                repos = api.getUserRepos(username).filterNot { it.isFork }
            } catch (e: Exception) { e.printStackTrace() }
        }
    }
}