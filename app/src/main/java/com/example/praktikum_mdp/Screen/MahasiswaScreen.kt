package com.example.praktikum_mdp.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.praktikum_mdp.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MahasiswaScreen(navController: NavController) {
    // Buat gradasi warna diagonal
    val gradientBackground = Brush.linearGradient(
        colors = listOf(
            Color(0xFF6A11CB), // Ungu
            Color(0xFF2575FC)  // Biru
        ),
        start = androidx.compose.ui.geometry.Offset(0f, 0f),
        end = androidx.compose.ui.geometry.Offset(1000f, 1000f)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Menu Mahasiswa", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        containerColor = Color.Transparent
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(gradientBackground)
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Pilih Menu Mahasiswa",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    navController.navigate(Screen.MahasiswaDaftar.route)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00C853))
            ) {
                Text("Daftar Mahasiswa", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD50000))
            ) {
                Text("Kembali", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MahasiswaScreenPreview() {
    MahasiswaScreen(navController = rememberNavController())
}
