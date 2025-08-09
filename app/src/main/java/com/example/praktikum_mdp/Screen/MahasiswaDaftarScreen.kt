package com.example.praktikum_mdp.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

data class Mahasiswa(
    var nama: String,
    var nim: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MahasiswaDaftarScreen(navController: NavController) {
    val daftarMahasiswa = remember { mutableStateListOf(
        Mahasiswa("Andi Pratama", "201110001"),
        Mahasiswa("Budi Santoso", "201110002"),
        Mahasiswa("Citra Dewi", "201110003"),
        Mahasiswa("Dian Prasetyo", "201110004"),
        Mahasiswa("Eka Wulandari", "201110005")
    ) }

    var namaInput by remember { mutableStateOf("") }
    var nimInput by remember { mutableStateOf("") }
    var editIndex by remember { mutableStateOf<Int?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Daftar Mahasiswa") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // Form Input
            OutlinedTextField(
                value = namaInput,
                onValueChange = { namaInput = it },
                label = { Text("Nama Mahasiswa") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = nimInput,
                onValueChange = { nimInput = it },
                label = { Text("NIM") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    if (editIndex == null) {
                        // CREATE
                        daftarMahasiswa.add(Mahasiswa(namaInput, nimInput))
                    } else {
                        // UPDATE
                        daftarMahasiswa[editIndex!!] = Mahasiswa(namaInput, nimInput)
                        editIndex = null
                    }
                    namaInput = ""
                    nimInput = ""
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (editIndex == null) "Tambah" else "Simpan Perubahan")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // READ
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(daftarMahasiswa) { mhs ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                        ) {
                            Text(text = mhs.nama, style = MaterialTheme.typography.bodyLarge)
                            Text(text = mhs.nim, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                TextButton(onClick = {
                                    // UPDATE
                                    namaInput = mhs.nama
                                    nimInput = mhs.nim
                                    editIndex = daftarMahasiswa.indexOf(mhs)
                                }) {
                                    Text("Edit", color = Color.Blue)
                                }
                                TextButton(onClick = {
                                    // DELETE
                                    daftarMahasiswa.remove(mhs)
                                }) {
                                    Text("Hapus", color = Color.Red)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MahasiswaDaftarScreenPreview() {
    MahasiswaDaftarScreen(navController = rememberNavController())
}
