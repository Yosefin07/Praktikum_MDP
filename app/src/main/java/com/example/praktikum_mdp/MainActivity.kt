package com.example.paktikummdp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.praktikum_mdp.ui.theme.Praktikum_MDPTheme
import kotlin.coroutines.coroutineContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Praktikum_MDPTheme {
                WelcomeScreen()
            }
        }
    }
}

@Composable
fun WelcomeScreen() {
    val context = LocalContext.current

    var nama by remember {mutableStateOf("") }
    Column (
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Text(
            text= "Selamat Datang di Mobile device programming",
            color = Color(0xFF512DA8),
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
        )

        OutlinedTextField(
            value = nama,
            label = {Text("Masukkan nama Anda...")},
            onValueChange = { nama = it },
            modifier = Modifier.fillMaxWidth()

        )

        Button(
            onClick = {
                Toast.makeText(context, "Halo, ", Toast.LENGTH_SHORT).show()
            }
        ) {
            Text(text = "Tampilkan Toast")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Praktikum_MDPTheme {
        WelcomeScreen()
    }
}