package com.example.composepoc.utils

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ButtonUi(
    modifier: Modifier, color: ButtonColors, textString: String,
    textColour: Color, textAlign : TextAlign, onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        colors = color,
        onClick = {
            onClick()
        }
    ) {

        Text(textString, color = textColour, textAlign = textAlign)
    }
}