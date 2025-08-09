package com.example.praktikum_mdp.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

data class Mitra(
    var nama: String,
    var bidang: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MitraScreen(navController: NavController) {
    val daftarMitra = remember { mutableStateListOf<Mitra>() }
    var namaInput by remember { mutableStateOf("") }
    var bidangInput by remember { mutableStateOf("") }
    var editIndex by remember { mutableStateOf<Int?>(null) }

    val gradientBackground = Brush.verticalGradient(
        colors = listOf(Color(0xFF4A90E2), Color(0xFF50E3C2))
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Halaman Mitra", color = Color.White) },
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
                .padding(16.dp)
        ) {
            // Form Input
            OutlinedTextField(
                value = namaInput,
                onValueChange = { namaInput = it },
                label = { Text("Nama Mitra", color = Color.White) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = bidangInput,
                onValueChange = { bidangInput = it },
                label = { Text("Bidang Usaha", color = Color.White) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    if (editIndex == null) {
                        daftarMitra.add(Mitra(namaInput, bidangInput))
                    } else {
                        daftarMitra[editIndex!!] = Mitra(namaInput, bidangInput)
                        editIndex = null
                    }
                    namaInput = ""
                    bidangInput = ""
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007AFF))
            ) {
                Text(if (editIndex == null) "Mendaftar" else "Simpan Perubahan", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // READ
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(daftarMitra) { mitra ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        elevation = CardDefaults.cardElevation(4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f))
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                        ) {
                            Text(text = mitra.nama, style = MaterialTheme.typography.bodyLarge, color = Color.Black)
                            Text(text = mitra.bidang, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                TextButton(onClick = {
                                    namaInput = mitra.nama
                                    bidangInput = mitra.bidang
                                    editIndex = daftarMitra.indexOf(mitra)
                                }) {
                                    Text("Edit", color = Color.Blue)
                                }
                                TextButton(onClick = {
                                    daftarMitra.remove(mitra)
                                }) {
                                    Text("Hapus", color = Color.Red)
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFff5e57))
            ) {
                Text("Kembali", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MitraScreenPreview() {
    MitraScreen(navController = rememberNavController())
}
