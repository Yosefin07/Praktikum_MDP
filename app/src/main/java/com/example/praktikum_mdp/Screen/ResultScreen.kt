package com.example.praktikum_mdp.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.praktikum_mdp.R
import com.example.praktikum_mdp.navigation.Screen

@Composable
fun ResultScreen(name: String, navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // 🖼 Background image
        Image(
            painter = painterResource(id = R.drawable.background_result),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // 📦 Konten yang diposisikan sedikit di bawah tengah
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f)) // Mendorong ke bawah

            // 🎯 Logo atas
            Image(
                painter = painterResource(id = R.drawable.logo_result),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(120.dp)
                    .padding(bottom = 24.dp)
            )

            // 👋 Sapaan pengguna
            Text(
                text = "Halo, $name!",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 🔙 Tombol kembali
            Button(
                onClick = {
                    navController.navigate(route = Screen.Home.route)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Kembali")
            }

            Spacer(modifier = Modifier.weight(2f)) // Tambah spasi bawah agar makin turun
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultPreview() {
    ResultScreen(
        name = "Preview",
        navController = rememberNavController()
    )
}
