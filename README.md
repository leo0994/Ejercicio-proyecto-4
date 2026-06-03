<h1 align="center">Aprende Estado en Compose</h1>
<h3 align="center">var · remember · rememberSaveable · State Hoisting</h3>

<p align="center">
  <a href="https://github.com/new?template_owner=kevinah95&template_name=learn-compose-state&owner=%40me&name=learn-compose-state&description=Mi+ejercicio+de+estado+en+Compose&visibility=public">
    <img src="https://img.shields.io/badge/Copiar%20ejercicio-%232ea44f?style=for-the-badge&logo=github&logoColor=white" alt="Copiar ejercicio"/>
  </a>
</p>

---

## Bienvenido 👋

En este ejercicio aprenderás a manejar el **estado** en Jetpack Compose / Compose Multiplatform de manera progresiva, partiendo de código roto hasta implementar el patrón correcto de **State Hoisting**.

> **¿Para quién es este ejercicio?**
> Estudiantes con conocimientos básicos de Kotlin que están comenzando con Compose Multiplatform.

---

## Lo que aprenderás

| # | Concepto | Lo que harás |
|---|---|---|
| 1 | Variables comunes de Kotlin | Observar por qué `var` no funciona en Compose |
| 2 | `remember` | Hacer que el estado sobreviva la recomposición |
| 3 | `rememberSaveable` | Hacer que el estado sobreviva rotaciones de pantalla |
| 4 | State Hoisting | Extraer el estado para tener Composables reutilizables |

---

## Prerrequisitos

Antes de comenzar necesitas:

- [ ] Conocimiento básico de Kotlin
- [ ] [Android Studio](https://developer.android.com/studio) con el plugin de Kotlin Multiplatform
- [ ] Un proyecto KMP con Compose Multiplatform (puedes usar el [wizard de JetBrains](https://kmp.jetbrains.com/))

---

## Cómo empezar

1. Haz clic en **"Copiar ejercicio"** arriba para crear tu propio repositorio desde esta plantilla.
2. Espera unos segundos a que GitHub Actions cree el **issue de ejercicio** automáticamente.
3. Sigue las instrucciones paso a paso en ese issue.
4. Cada vez que hagas un `push` a `main`, el flujo de trabajo verificará tu avance.

---

## Estructura del proyecto

```
learn-compose-state/
├── composeApp/
│   └── src/
│       └── commonMain/
│           └── kotlin/
│               └── org/example/composestate/
│                   ├── App.kt                  ← punto de entrada (no modificar)
│                   └── screens/
│                       └── ContadorScreen.kt   ← aquí trabajarás
└── .github/
    ├── steps/                                  ← instrucciones de cada paso
    └── workflows/                              ← automatización del ejercicio
```

---

## ¿Tienes dudas?

Abre un issue en este repositorio o consulta la [documentación oficial de Compose](https://developer.android.com/develop/ui/compose/state).
