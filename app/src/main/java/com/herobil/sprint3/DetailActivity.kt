package com.herobil.sprint3

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.herobil.sprint3.databinding.ActivityDetailBinding

/**
 * DetailActivity – cartelera de combates usando el componente propio MyCard (Reto 9).
 *
 *  - Las dos primeras cards se declaran en XML con atributos propios (app:mcTitle...).
 *  - Las imágenes se añaden por código con card.setImage(...).
 *  - La tercera card se crea completamente por código para demostrar que el componente
 *    es flexible (sin imagen: el hueco se oculta y la card se adapta).
 */
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setAccessibilityHeading(binding.tvHeader, true)

        // Imagen añadida de forma programática, con descripción para accesibilidad
        binding.cardFight1.setImage(R.drawable.img_glove_red, getString(R.string.fight1_image_desc))
        binding.cardFight2.setImage(R.drawable.img_glove_blue, getString(R.string.fight2_image_desc))

        // Card creada 100% por código
        val cardFight3 = MyCard(this).apply {
            title = getString(R.string.fight3_title)
            subtitle = getString(R.string.fight3_subtitle)
            description = getString(R.string.fight3_desc)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { topMargin = resources.getDimensionPixelSize(R.dimen.space_m) }
        }
        binding.llCards.addView(cardFight3)
    }
}
