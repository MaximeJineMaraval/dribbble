package com.jine.dribbble

import androidx.compose.ui.graphics.Brush
import com.jine.dribbble.financeconcept.FinanceConceptPink
import com.jine.dribbble.financeconcept.FinanceConceptPurple

sealed class Destinations(val title: String, val background: Brush?) {
    object Menu : Destinations(
        title = "Menu",
        background = null
    )

    object FinanceConcept : Destinations(
        title = "Finance Concept",
        background = Brush.linearGradient(listOf(FinanceConceptPink, FinanceConceptPurple))
    )
}