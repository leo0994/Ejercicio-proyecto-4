# Paso 2 — `remember` para sobrevivir la recomposición

¡Buen trabajo identificando el problema! 🎉

Ahora vamos a solucionarlo usando `remember` junto con `mutableStateOf`.

## ¿Qué hace `remember`?

`remember` almacena un valor **dentro del árbol de composición**. Cuando Compose recompone el Composable, en lugar de calcular el valor de nuevo, devuelve el valor que guardó la primera vez.

```
Primera ejecución  → calcula el valor → lo guarda en el árbol
Recomposiciones    → devuelve el valor guardado directamente
Sale del árbol     → el valor se descarta
```

## La solución con `remember` 🟢

Modifica `ContadorScreen.kt` para usar `remember { mutableStateOf(0) }`:

```kotlin
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun ContadorScreen() {
    // ✅ Ahora Compose guarda este valor entre recomposiciones
    //    y sabe cuándo debe recomponer (cuando count cambia)
    var count by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Contador: $count",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { count++ }) {
            Text("Incrementar")
        }
    }
}
```

## Tu tarea ✅

1. Reemplaza `var count = 0` con `var count by remember { mutableStateOf(0) }`.
2. Agrega los imports necesarios.
3. Ejecuta la app y confirma que el contador **ya no se resetea**.
4. Haz commit y push:

```bash
git add .
git commit -m "paso-2: uso remember para preservar el estado"
git push
```

<details>
<summary>💡 ¿Qué diferencia hay entre remember y mutableStateOf?</summary>

Son dos cosas distintas que trabajan juntas:

- **`mutableStateOf(0)`** crea un objeto `MutableState<Int>` que Compose **observa**. Cuando su valor cambia, Compose sabe que debe recomponer.
- **`remember { ... }`** hace que ese objeto **no se recree** en cada recomposición — Compose lo guarda en el árbol y lo reutiliza.

Sin `remember`, crearías un nuevo `mutableStateOf(0)` en cada recomposición (valor siempre 0).
Sin `mutableStateOf`, el valor no notificaría a Compose de los cambios.

</details>

<details>
<summary>💡 ¿Qué es la sintaxis `by`?</summary>

`by` usa la delegación de propiedades de Kotlin. Con `by`, puedes leer y escribir `count` directamente como si fuera un `Int`, en vez de tener que usar `count.value` cada vez.

```kotlin
// Sin `by`:
val count = remember { mutableStateOf(0) }
count.value++
Text("${count.value}")

// Con `by`:
var count by remember { mutableStateOf(0) }
count++
Text("$count")
```

</details>
