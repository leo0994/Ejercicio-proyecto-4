# Paso 4 — State Hoisting: el patrón correcto

¡Casi terminas! 🚀 Este es el paso más importante.

Hasta ahora, `ContadorScreen` guarda su propio estado internamente. Esto funciona, pero dificulta **reutilizar** y **probar** el Composable. El patrón **State Hoisting** soluciona esto.

## ¿Qué es State Hoisting?

State Hoisting = **mover el estado hacia arriba** en el árbol de composición.

La idea es separar el Composable en dos:

| | Composable **Stateful** | Composable **Stateless** |
|---|---|---|
| Tiene estado | ✅ Sí (`remember`, `rememberSaveable`) | ❌ No |
| Recibe datos | No necesita | ✅ Como parámetros |
| Emite eventos | No necesita | ✅ Como lambdas |
| Se puede probar | Difícil | ✅ Fácilmente |
| Se puede reutilizar | Limitado | ✅ En cualquier lugar |

## El flujo de datos

```
Estado baja  ↓      Eventos suben  ↑

ContadorScreen()        ← stateful, dueño del estado
       │
       │  count, nombre, onIncrement, onNombreChange
       ↓
ContadorView()          ← stateless, solo UI
```

## Tu tarea ✅

Crea el archivo `composeApp/src/commonMain/kotlin/org/example/composestate/screens/ContadorView.kt` con el Composable stateless:

```kotlin
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
```

Luego actualiza `ContadorScreen.kt` para que sea el **stateful** que usa `ContadorView`:

```kotlin
package io.github.kevinah95.myapplication.screens

import androidx.compose.runtime.*

// ✅ Stateful: dueño del estado. Delega la UI a ContadorView.
@Composable
fun ContadorScreen() {
    var count by rememberSaveable { mutableStateOf(0) }
    var nombre by rememberSaveable { mutableStateOf("") }

    ContadorView(
        count = count,
        nombre = nombre,
        onIncrement = { count++ },
        onNombreChange = { nombre = it }
    )
}
```

Haz commit y push:

```bash
git add .
git commit -m "paso-4: aplico state hoisting"
git push
```

<details>
<summary>💡 ¿Por qué vale la pena separar stateful y stateless?</summary>

**Reutilización:** `ContadorView` puede usarse con cualquier fuente de estado — un ViewModel, otro Composable, o directamente en un preview.

```kotlin
// En un preview — sin necesitar estado real
@Preview
@Composable
fun ContadorViewPreview() {
    ContadorView(
        count = 42,
        nombre = "Kotlin",
        onIncrement = {},
        onNombreChange = {}
    )
}
```

**Pruebas:** puedes probar `ContadorView` pasando valores fijos, sin necesidad de simular interacciones complejas de estado.

</details>

<details>
<summary>💡 ¿Y el ViewModel dónde entra?</summary>

En apps reales, el estado que necesita sobrevivir a la navegación o que implica lógica de negocio vive en el **ViewModel** (en `commonMain` en KMP). El Composable stateful (`ContadorScreen`) simplemente obtiene el estado del ViewModel y llama a sus funciones:

```kotlin
@Composable
fun ContadorScreen(viewModel: ContadorViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    ContadorView(
        count = uiState.count,
        nombre = uiState.nombre,
        onIncrement = viewModel::incrementar,
        onNombreChange = viewModel::actualizarNombre
    )
}
```

</details>
