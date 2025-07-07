package com.example.composepoc.view

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun MediumText(
    modifier: Modifier,
    textString: String,
    color: Color,
    textAlign: TextAlign? = null
) {
    Text(modifier = modifier, text = textString, color = Color.Blue, style = MaterialTheme.typography.titleMedium, textAlign = textAlign)
}