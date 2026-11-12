package com.herobil.sprint3

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import androidx.core.view.ViewCompat
import com.google.android.material.card.MaterialCardView
import com.herobil.sprint3.databinding.ViewMyCardBinding

/**
 * MyCard – componente propio reutilizable (Reto 9).
 *
 * Se puede configurar de dos formas:
 *  - Desde XML:  app:mcTitle, app:mcSubtitle, app:mcDescription, app:mcImage, app:mcImageDescription
 *  - Desde código: card.title = "...", card.setImage(R.drawable.x, "descripción"), etc.
 *
 * Todos los campos son opcionales: si un texto o la imagen están vacíos, su vista se oculta
 * (View.GONE) y la card se adapta sola. Así sirve tanto para una card completa como para
 * una que solo tenga título.
 *
 * Usa ViewBinding (ViewMyCardBinding) para acceder a sus vistas internas.
 */
class MyCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = com.google.android.material.R.attr.materialCardViewStyle
) : MaterialCardView(context, attrs, defStyleAttr) {

    // El layout usa <merge>, por eso se infla pasando "this" como padre
    private val binding = ViewMyCardBinding.inflate(LayoutInflater.from(context), this)

    var title: CharSequence?
        get() = binding.tvTitle.text
        set(value) = binding.tvTitle.showOrHide(value)

    var subtitle: CharSequence?
        get() = binding.tvSubtitle.text
        set(value) = binding.tvSubtitle.showOrHide(value)

    var description: CharSequence?
        get() = binding.tvDescription.text
        set(value) = binding.tvDescription.showOrHide(value)

    init {
        // Estilo visual de la card
        setCardBackgroundColor(ContextCompat.getColor(context, R.color.velada_surface))
        radius = resources.getDimension(R.dimen.corner_m)
        cardElevation = 0f
        strokeWidth = resources.getDimensionPixelSize(R.dimen.space_xs) / 4
        strokeColor = ContextCompat.getColor(context, R.color.velada_gold_dark)

        // Accesibilidad: TalkBack lee la card como un único bloque (título + subtítulo + descripción)
        isFocusable = true
        ViewCompat.setAccessibilityHeading(binding.tvTitle, true)

        // Lectura de los atributos personalizados definidos en attrs.xml
        context.withStyledAttributes(attrs, R.styleable.MyCard) {
            title = getString(R.styleable.MyCard_mcTitle)
            subtitle = getString(R.styleable.MyCard_mcSubtitle)
            description = getString(R.styleable.MyCard_mcDescription)
            val imageDesc = getString(R.styleable.MyCard_mcImageDescription)
            setImage(getDrawable(R.styleable.MyCard_mcImage), imageDesc)
        }
    }

    // ---------- Imagen (se puede añadir de forma programática) ----------

    /** Imagen desde un recurso drawable. */
    fun setImage(@DrawableRes resId: Int, description: CharSequence? = null) {
        setImage(ContextCompat.getDrawable(context, resId), description)
    }

    /** Imagen desde un Drawable ya creado. Si es null, se oculta el hueco de la imagen. */
    fun setImage(drawable: Drawable?, description: CharSequence? = null) {
        binding.ivImage.setImageDrawable(drawable)
        binding.ivImage.visibility = if (drawable == null) GONE else VISIBLE
        applyImageDescription(description)
    }

    /** Imagen desde un Bitmap (por ejemplo, una foto descargada o de la cámara). */
    fun setImage(bitmap: Bitmap, description: CharSequence? = null) {
        binding.ivImage.setImageBitmap(bitmap)
        binding.ivImage.visibility = VISIBLE
        applyImageDescription(description)
    }

    fun clearImage() = setImage(null as Drawable?)

    /**
     * Si la imagen aporta información se le pone contentDescription.
     * Si es decorativa (sin descripción), se excluye de TalkBack para no leer ruido.
     */
    private fun applyImageDescription(description: CharSequence?) {
        binding.ivImage.contentDescription = description
        binding.ivImage.importantForAccessibility =
            if (description.isNullOrBlank()) IMPORTANT_FOR_ACCESSIBILITY_NO
            else IMPORTANT_FOR_ACCESSIBILITY_YES
    }

    private fun TextView.showOrHide(value: CharSequence?) {
        text = value
        visibility = if (value.isNullOrBlank()) GONE else VISIBLE
    }
}
