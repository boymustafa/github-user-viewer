package com.boy.githubuserviewer

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun GitHubApp() {
    val navController = rememberNavController()
    val viewModel: MainViewModel = hiltViewModel()

    NavHost(navController, startDestination = Screen.Search.route) {
        composable(Screen.Search.route) {
            UserSearchScreen(viewModel) { username ->
                navController.navigate(Screen.Detail.createRoute(username))
            }
        }
        composable(Screen.Detail.route, arguments = listOf(navArgument("username") { type = NavType.StringType })) {
            val username = it.arguments?.getString("username") ?: return@composable
            viewModel.loadUserDetail(username)
            UserDetailScreen(viewModel)
        }
    }
}