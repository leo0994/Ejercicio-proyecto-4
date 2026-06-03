# 🎉 ¡Ejercicio completado!

¡Felicitaciones! Has completado el ejercicio de Estado en Compose. Esto es lo que lograste:

## Resumen de lo aprendido

### Paso 1 — Variables comunes
Las variables locales `var` se **reinician en cada recomposición**. No sirven como estado de UI porque:
- No notifican a Compose cuando cambian
- Se destruyen y recrean en cada ejecución del Composable

### Paso 2 — `remember`
`remember { mutableStateOf(...) }` resuelve el problema:
- `mutableStateOf` crea un objeto que Compose **observa**
- `remember` evita que se recree en cada recomposición

```kotlin
var count by remember { mutableStateOf(0) }
```

### Paso 3 — `rememberSaveable`
`rememberSaveable` va un paso más allá — guarda el estado en el `SavedStateHandle`:
- ✅ Sobrevive recomposiciones
- ✅ Sobrevive rotaciones de pantalla
- ✅ Sobrevive que el sistema mate el proceso en segundo plano

```kotlin
var nombre by rememberSaveable { mutableStateOf("") }
```

### Paso 4 — State Hoisting
El patrón correcto para Composables reutilizables y testeables:
- **Stateful**: dueño del estado (`remember`, `rememberSaveable`, ViewModel)
- **Stateless**: solo recibe datos y emite eventos como parámetros

```
estado baja ↓          eventos suben ↑
ContadorScreen  ──────────►  ContadorView
```

## Tabla comparativa final

| | `var` | `remember` | `rememberSaveable` |
|---|:---:|:---:|:---:|
| Sobrevive recomposición | ❌ | ✅ | ✅ |
| Notifica a Compose | ❌ | ✅ | ✅ |
| Sobrevive rotación | ❌ | ❌ | ✅ |
| Sobrevive proceso en background | ❌ | ❌ | ✅ |

## Próximos pasos sugeridos

- 📖 [State and Jetpack Compose](https://developer.android.com/develop/ui/compose/state)
- 📖 [Lifecycle of composables](https://developer.android.com/develop/ui/compose/lifecycle)
- 🛠️ Integra un `ViewModel` en `commonMain` y conéctalo con `ContadorView`
- 🧪 Escribe un test para `ContadorView` pasando parámetros directamente

---

_Ejercicio creado para BISOFT-38 · Proyecto IV · Universidad_
