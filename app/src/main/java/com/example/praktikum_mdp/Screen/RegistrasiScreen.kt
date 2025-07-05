package com.example.praktikum_mdp.Screen

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.praktikum_mdp.R
import com.example.praktikum_mdp.navigation.Screen
import com.example.praktikum_mdp.service.Api.ApiClient
import com.example.praktikum_mdp.model.request.RegisterRequest
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(navController: NavController) {
    var fullName by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var fullNameError by remember { mutableStateOf(false) }
    var usernameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var confirmPasswordError by remember { mutableStateOf(false) }

    var isLoading by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background_register),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .imePadding(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.92f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_registrasi),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(100.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    Text(
                        text = "Form Registrasi",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = fullName,
                        onValueChange = {
                            fullName = it
                            fullNameError = false
                        },
                        isError = fullNameError,
                        label = { Text("Nama Lengkap") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (fullNameError) {
                        Text("Nama lengkap wajib diisi", color = MaterialTheme.colorScheme.error)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = username,
                        onValueChange = {
                            username = it
                            usernameError = false
                        },
                        isError = usernameError,
                        label = { Text("Username") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (usernameError) {
                        Text("Username wajib diisi", color = MaterialTheme.colorScheme.error)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                            emailError = false
                        },
                        isError = emailError,
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (emailError) {
                        Text("Email tidak valid", color = MaterialTheme.colorScheme.error)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            passwordError = false
                        },
                        isError = passwordError,
                        label = { Text("Password") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (passwordError) {
                        Text("Password wajib diisi", color = MaterialTheme.colorScheme.error)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = {
                            confirmPassword = it
                            confirmPasswordError = false
                        },
                        isError = confirmPasswordError,
                        label = { Text("Konfirmasi Password") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (confirmPasswordError) {
                        Text("Password tidak cocok", color = MaterialTheme.colorScheme.error)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            focusManager.clearFocus()

                            fullNameError = fullName.isBlank()
                            usernameError = username.isBlank()
                            emailError = email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()
                            passwordError = password.isBlank()
                            confirmPasswordError = confirmPassword != password

                            if (!fullNameError && !usernameError && !emailError && !passwordError && !confirmPasswordError) {
                                isLoading = true
                                coroutineScope.launch {
                                    try {
                                        val response = ApiClient.instance.register(
                                            RegisterRequest(
                                                nm_lengkap = fullName,
                                                email = email,
                                                username = username,
                                                password = password
                                            )
                                        )
                                        isLoading = false
                                        val body = response.body()
                                        if (response.isSuccessful && body != null) {
                                            Toast.makeText(context, "Registrasi berhasil!", Toast.LENGTH_SHORT).show()
                                            navController.navigate(Screen.Login.route) {
                                                popUpTo(Screen.Registrasi.route) { inclusive = true }
                                            }
                                        } else {
                                            Toast.makeText(context, "Gagal: ${response.message()}", Toast.LENGTH_LONG).show()
                                        }
                                    } catch (e: Exception) {
                                        isLoading = false
                                        Toast.makeText(context, "Error: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                                    }
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Register")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    TextButton(
                        onClick = { navController.navigate(Screen.Login.route) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Sudah punya akun? Login")
                    }
                }
            }
        }

        if (isLoading) {
            AlertDialog(
                onDismissRequest = {},
                confirmButton = {},
                title = { Text("Mohon tunggu") },
                text = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        CircularProgressIndicator(modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(16.dp))
                        Text("Sedang mengirim data...")
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(navController = rememberNavController())
}
