# Paso 3 — `rememberSaveable` para sobrevivir rotaciones de pantalla

¡Excelente! Ya el contador funciona. 🎯

Pero hay un caso que `remember` no cubre: **cuando el usuario rota la pantalla** (o el sistema destruye y recrea la Activity). En ese caso, el árbol de composición se destruye y todo el estado guardado con `remember` se pierde.

## El problema de la rotación 📱

Prueba esto:
1. Incrementa el contador algunas veces.
2. Rota el dispositivo/emulador (o presiona el botón de rotación).
3. Observa que **el contador vuelve a 0**.

Esto ocurre porque `remember` solo vive mientras el Composable está en el árbol. Al rotar, Android destruye y recrea la Activity, y el árbol se construye de nuevo desde cero.

## La solución: `rememberSaveable` 🟢

`rememberSaveable` guarda el valor en el `SavedStateHandle` (Bundle en Android), que sobrevive a rotaciones y a que el sistema mate el proceso en segundo plano.

## Tu tarea ✅

Agrega un `TextField` para que el usuario escriba su nombre, usando `rememberSaveable` para que el texto no se pierda al rotar:

```kotlin
import androidx.compose.runtime.rememberSaveable

@Composable
fun ContadorScreen() {
    var count by remember { mutableStateOf(0) }

    // ✅ rememberSaveable: sobrevive rotaciones de pantalla
    var nombre by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Campo de nombre — no se pierde al rotar
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
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
        Button(onClick = { count++ }) {
            Text("Incrementar")
        }
    }
}
```

Luego prueba:
1. Escribe tu nombre en el campo.
2. Incrementa el contador.
3. Rota la pantalla.
4. ✅ El nombre se mantiene — ❓ ¿y el contador?

Haz commit y push:

```bash
git add .
git commit -m "paso-3: uso rememberSaveable para el nombre"
git push
```

<details>
<summary>💡 ¿Por qué el contador sí se pierde aunque use remember?</summary>

`count` usa `remember`, que solo vive en el árbol de composición. Al rotar, el árbol se destruye. Para que el contador también sobreviva, cámbialo a `rememberSaveable`:

```kotlin
var count by rememberSaveable { mutableStateOf(0) }
```

</details>

<details>
<summary>💡 ¿rememberSaveable funciona con cualquier tipo?</summary>

Solo funciona automáticamente con tipos que se pueden serializar en un `Bundle`: `Int`, `String`, `Boolean`, `Float`, listas de primitivos, etc.

Para tipos personalizados necesitas un `Saver`. Ejemplo:

```kotlin
data class Punto(val x: Int, val y: Int)

val PuntoSaver = listSaver<Punto, Int>(
    save    = { listOf(it.x, it.y) },
    restore = { Punto(it[0], it[1]) }
)

var punto by rememberSaveable(stateSaver = PuntoSaver) {
    mutableStateOf(Punto(0, 0))
}
```

</details>
