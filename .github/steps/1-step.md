# Paso 1 — Variables comunes y el problema de recomposición

¡Hola! 👋 Bienvenido al primer paso.

En este paso vas a **experimentar de primera mano** por qué las variables comunes de Kotlin no funcionan como estado en Compose.

## ¿Qué es la recomposición?

Compose redibuja la UI ejecutando de nuevo la función `@Composable` cada vez que detecta un cambio de estado. A este proceso se le llama **recomposición**.

> El problema: una variable local `var` se **recrea desde cero** en cada recomposición. Cualquier valor que hayas acumulado se pierde.

## El código roto 🔴

Abre el archivo `composeApp/src/commonMain/kotlin/org/example/composestate/screens/ContadorScreen.kt`. Verás este código:

```kotlin
@Composable
fun ContadorScreen() {
    var count = 0 // ← Esta variable se reinicia en cada recomposición

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

Observa el problema y confirma que lo entiendes **agregando un comentario** en el archivo que explique por qué `var count = 0` no funciona.

En la línea donde está `var count = 0`, cambia el comentario existente por uno propio. Por ejemplo:

```kotlin
var count = 0 // No funciona: se reinicia a 0 en cada recomposición
```

Luego haz commit y push a `main`:

```bash
git add .
git commit -m "paso-1: identifico el problema con var"
git push
```

<details>
<summary>💡 ¿Por qué falla también el onClick?</summary>

`count++` sí modifica la variable local, **pero Compose nunca se entera del cambio** porque `count` no es un objeto de estado observable. Compose solo recompone cuando lee un `State<T>`. Como `count` es un `Int` plano, el botón incrementa un valor que nadie observa y que se tira a la basura en la siguiente recomposición.

</details>
