package com.example.composepoc.view

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun TextInputField(
    modifier: Modifier,
    value: String,
    onTextValueChange: (textFieldValue: String) -> Unit
) {

    val textFieldValue = remember { mutableStateOf(value) }

    TextField(
        modifier = modifier,
        value = textFieldValue.value,
        onValueChange = {
            textFieldValue.value = it
            onTextValueChange(textFieldValue.value)
        },
        label = {
            Text("Enter Name")
        },
        placeholder = { Text("Enter name") },
    )


}