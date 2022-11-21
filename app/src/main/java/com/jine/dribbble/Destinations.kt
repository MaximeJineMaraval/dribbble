package com.jine.dribbble

import androidx.compose.ui.graphics.Brush
import com.jine.dribbble.eyecream.EyeCreamGradiant
import com.jine.dribbble.financeconcept.FinanceConceptGradiant
import com.jine.dribbble.financeconcept.FinanceConceptPink
import com.jine.dribbble.financeconcept.FinanceConceptPurple

sealed class Destinations(val title: String, val background: Brush?) {
    object Menu : Destinations(
        title = "Menu",
        background = null
    )

    object FinanceConcept : Destinations(
        title = "Finance Concept",
        background = FinanceConceptGradiant
    )

    object EyeCream : Destinations(
        title = "Eye Cream",
        background = EyeCreamGradiant
    )
}