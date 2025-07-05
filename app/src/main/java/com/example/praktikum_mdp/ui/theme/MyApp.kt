package com.example.praktikum_mdp.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.praktikum_mdp.navigation.Screen
import com.example.praktikum_mdp.Components.BottomBar
import com.example.praktikum_mdp.navigation.SetupNavGraph

/**
 * Fungsi utama untuk menjalankan struktur UI aplikasi.
 * Menampilkan navigasi dan bottom bar sesuai dengan halaman yang sedang aktif.
 *
 * @param navController controller navigasi yang mengelola perpindahan antar layar.
 */
@Composable
fun MyApp(navController: NavHostController) {
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route
    val showBottomBar = currentRoute in listOf(Screen.Home.route, Screen.Profile.route)

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            containerColor = Color.Transparent,
            bottomBar = {
                if (showBottomBar) {
                    BottomBar(
                        navController = navController,
                        modifier = Modifier
                            .windowInsetsPadding(WindowInsets.navigationBars)
                    )
                }
            }
        ) { contentPadding ->
            SetupNavGraph(
                navController = navController,
                modifier = Modifier.padding(contentPadding)
            )
        }
    }
}
