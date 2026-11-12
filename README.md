# Sprint 3 – Desarrollo de Interfaces (2º DAM)

Proyecto Android (Kotlin + XML) con los retos del Sprint 3.

## Reto 7 – HomeActivity
Pantalla principal sobre **La Velada del Año 2027**: imagen, etiqueta, título, subtítulo y botón
"Comprar entradas", que muestra un Toast ("Todavía no está implementado").
Los componentes están personalizados con shapes (`res/drawable/bg_*.xml`).
Los principios de diseño aplicados están comentados en `HomeActivity.kt`.

## Reto 9 – MyCard + DetailActivity
- `MyCard.kt`: componente propio (extiende `MaterialCardView`) con título, subtítulo, descripción
  e imagen opcionales. Se configura desde XML (`app:mcTitle`, `app:mcSubtitle`, `app:mcDescription`,
  `app:mcImage`) o desde código. Usa ViewBinding.
- `DetailActivity.kt`: usa MyCard. Las imágenes se añaden por código con `setImage(...)`
  y la tercera card se crea completamente por código.
- Se accede a DetailActivity desde el botón "Ver combates" de HomeActivity.

## Accesibilidad
contentDescription en las imágenes informativas, encabezados para TalkBack, zonas táctiles ≥ 48dp,
textos en sp, contraste alto y contenido en ScrollView.
