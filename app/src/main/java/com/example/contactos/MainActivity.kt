package com.example.contactos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.example.contactos.ui.theme.ContactosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContactosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Contactos",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    HeaderLazy()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeaderLazy() {

    val contactos: Map<String, List<Contactos>> =
        getContactos().groupBy { it.nombre.first().toString() }

    LazyColumn(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .systemBarsPadding()
    ) {

        contactos.forEach { nombre, contactos ->

            stickyHeader {
                Text(
                    text = nombre,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF6200EE))
                        .padding(8.dp)
                )
            }

            items(contactos) { contacto ->
                ItemContactos(model = contacto)
            }
        }
    }
}

@Composable
fun ItemContactos(model: Contactos) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .background(Color.White),
        border = BorderStroke(1.dp, Color(0xFF6200EE)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = model.nombre,
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = model.telefono.toString(),
                    style = TextStyle(fontSize = 16.sp, color = Color.Gray)
                )
            }

            Icon(
                imageVector = Icons.Default.Phone,
                contentDescription = "Llamar",
                modifier = Modifier.size(24.dp),
                tint = Color(0xFF6200EE)
            )
        }
    }
}

fun getContactos(): List<Contactos> {
    return listOf(
        Contactos("Pepe", 987234243),
        Contactos("Ana", 987654321),
        Contactos("Luis", 912345678),
        Contactos("María", 687453123),
        Contactos("Carlos", 601234567),
        Contactos("Sofía", 729384562),
        Contactos("Juan", 689234567),
        Contactos("Elena", 947381256),
        Contactos("Pablo", 621394857),
        Contactos("Lucía", 765432198),
        Contactos("Miguel", 932847561),
        Contactos("Clara", 698712345),
        Contactos("Diego", 652394871),
        Contactos("Sara", 910293847),
        Contactos("Raúl", 612345789),
        Contactos("Laura", 734569182),
        Contactos("Javier", 843256719),
        Contactos("Natalia", 609384725),
        Contactos("Andrés", 793284165),
        Contactos("Irene", 687192345),
        Contactos("Víctor", 921385467)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ContactosTheme {
        Greeting("Contactos")
    }
}
