package com.example.praktikum_mdp.navigation

sealed class Screen(val route: String) {
    object Home : Screen(route = "home")
    object Result : Screen(route = "result") {
        fun passText(name: String): String {
            return "result/$name"
        }
    }
    object Profile : Screen(route = "profile")
    object Registrasi : Screen(route = "registrasi")
    object Login : Screen(route = "login")
}