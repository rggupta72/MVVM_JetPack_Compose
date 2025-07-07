package com.example.composepoc.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.composepoc.utils.COLOR_BLACK

@Preview(showBackground = true, backgroundColor = COLOR_BLACK)
@Composable
fun HeadingText(modifier: Modifier, text: String, textColour: Color, textStyle: TextStyle, textAlign: TextAlign? = null) {

    Text(modifier = modifier, text = text, color = textColour, style = textStyle, textAlign = textAlign)

}