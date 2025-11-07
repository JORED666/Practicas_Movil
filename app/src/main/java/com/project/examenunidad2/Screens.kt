package com.project.examenunidad2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DashboardScreen(
    goTheme: () -> Unit,
    goUsers: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center // 🔹 Centra verticalmente
    ) {
        Text(
            "Dashboard",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(Modifier.height(24.dp))

        ElevatedCard(
            modifier = Modifier.fillMaxWidth(0.9f) // 🔹 90% del ancho para estética
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally // 🔹 Centra el contenido
            ) {
                Text("Vista 2: Preferencias de tema")
                Spacer(Modifier.height(8.dp))
                Button(onClick = goTheme) {
                    Text("Ir a Tema")
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        ElevatedCard(
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Vista 3: Usuarios (Room)")
                Spacer(Modifier.height(8.dp))
                Button(onClick = goUsers) {
                    Text("Ir a Usuarios")
                }
            }
        }
    }
}

@Composable
fun ThemeScreen(
    isDark: Boolean,
    onToggle: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            "Tema de la App",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(Modifier.height(12.dp))

        ElevatedCard(Modifier.fillMaxWidth()) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Activar tema oscuro", modifier = Modifier.weight(1f))
                Switch(checked = isDark, onCheckedChange = { onToggle() })
            }
        }

        Spacer(Modifier.height(24.dp))
        Button(
            onClick = onBack,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Volver")
        }
    }
}
