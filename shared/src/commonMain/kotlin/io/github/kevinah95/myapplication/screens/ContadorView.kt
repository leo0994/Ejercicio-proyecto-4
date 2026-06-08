package io.github.kevinah95.myapplication.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// ✅ Stateless: no tiene estado propio.
//    Recibe todo lo que necesita como parámetros.
@Composable
fun ContadorView(
    count: Int,
    nombre: String,
    onIncrement: () -> Unit,
    onNombreChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = nombre,
            onValueChange = onNombreChange,
            label = { Text("Tu nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = if (nombre.isBlank()) "Contador: $count"
                   else "Hola $nombre, tu contador: $count",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onIncrement) {
            Text("Incrementar")
        }
    }
}

