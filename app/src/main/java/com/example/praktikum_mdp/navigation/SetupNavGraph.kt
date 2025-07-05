package com.example.praktikum_mdp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.praktikum_mdp.Screen.*

@Composable
fun SetupNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route, // Mulai dari Login
        modifier = modifier
    ) {
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(Screen.Registrasi.route) {
            RegisterScreen(navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController) // BottomBar muncul otomatis di sini
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController) // BottomBar muncul otomatis di sini
        }
        composable(
            route = Screen.Result.route + "/{name}",
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) {
            val name = it.arguments?.getString("name") ?: ""
            ResultScreen(name = name, navController = navController)
        }
    }
}

