package com.example.composepoc.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CardWithText(
    modifier: Modifier = Modifier,
    shape: Shape = CardDefaults.shape,
    colors: Color = Color.White,
    elevation: Dp = 8.dp,
    border: BorderStroke? = null,
    content: @Composable ColumnScope.() -> Unit
) {

    Card(
        modifier = modifier,
        shape = shape,
        colors = CardDefaults.cardColors(colors),
        elevation = CardDefaults.cardElevation(elevation),
        border = border,
        content = content
    )

}