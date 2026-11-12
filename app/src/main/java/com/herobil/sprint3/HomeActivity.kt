package com.herobil.sprint3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.herobil.sprint3.databinding.ActivityHomeBinding

/*
 * HomeActivity – Pantalla principal de "La Velada del Año 2027" (Reto 7)
 *
 * PRINCIPIOS Y CONSIDERACIONES DE DISEÑO
 *
 * 1. Accesibilidad
 *    - La imagen tiene contentDescription para que TalkBack la describa.
 *    - El título se marca como "heading", así TalkBack permite saltar entre encabezados.
 *    - Los botones miden mínimo 56dp de alto (Material recomienda ≥ 48dp de zona táctil).
 *    - Los textos van en sp, así respetan el tamaño de letra que elija el usuario.
 *    - Contraste alto: texto blanco/dorado sobre fondo casi negro (ratio > 4.5:1, WCAG AA).
 *    - La pantalla va dentro de un ScrollView: con letra grande o en horizontal no se corta nada.
 *    - fitsSystemWindows evita que el contenido quede debajo de la barra de estado.
 *
 * 2. UI (interfaz)
 *    - Jerarquía visual clara: imagen → etiqueta → título → subtítulo → acción.
 *    - Paleta reducida y coherente (negro + dorado) que transmite "evento premium/nocturno".
 *    - Shapes personalizados: degradado de fondo, marco con borde dorado en la imagen,
 *      etiqueta tipo píldora y botón con degradado y esquinas redondeadas.
 *    - Espaciado consistente basado en múltiplos de 4/8dp (dimens.xml).
 *
 * 3. UX (experiencia)
 *    - Una sola acción principal destacada (botón dorado relleno) y una secundaria con
 *      menos peso visual (botón con borde), para no competir entre ellas.
 *    - Botones en la parte inferior, en la zona cómoda para el pulgar.
 *    - Feedback inmediato: el botón cambia de color al pulsarlo (selector) y muestra un Toast.
 *    - Textos cortos y claros; el botón dice exactamente lo que hace ("Comprar entradas").
 *
 * 4. Buenas prácticas
 *    - Nada de textos, colores ni medidas "a pelo": todo en strings, colors, dimens y styles.
 *    - Estilos reutilizables (Velada.Title, Velada.Subtitle) para no repetir atributos.
 *    - ViewBinding en lugar de findViewById (seguro frente a nulos y tipos).
 *    - Kotlin + XML, nombres de ids descriptivos con prefijo del tipo (tv, iv, btn).
 *    - Imágenes vectoriales (VectorDrawable): no pierden calidad y pesan poco.
 */
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Accesibilidad: el título se anuncia como encabezado en TalkBack
        ViewCompat.setAccessibilityHeading(binding.tvTitle, true)

        // Botón principal: funcionalidad todavía no implementada
        binding.btnTickets.setOnClickListener {
            Toast.makeText(this, R.string.toast_not_implemented, Toast.LENGTH_SHORT).show()
        }

        // Botón secundario: abre la cartelera (Reto 9)
        binding.btnDetail.setOnClickListener {
            startActivity(Intent(this, DetailActivity::class.java))
        }
    }
}
