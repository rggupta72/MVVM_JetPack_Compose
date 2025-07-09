package com.example.composepoc.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TextInputField(
    modifier: Modifier = Modifier,
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

@Composable
fun TWTextField(
    label: String,
    text: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isFocused: Boolean = false,
    maxLength: Int = -1,
    labelContentDescription: String? = null,
    errorMessage: String? = null,
    supportingText: String? = null,
    isEnabled: Boolean = true,
    keyboardOptions: KeyboardOptions? = null,
    keyboardActions: KeyboardActions? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {

    val focusRequester = remember { FocusRequester() }
    if (isFocused) {
        LaunchedEffect(key1 = true) {
            focusRequester.requestFocus()
        }
    }

    TMOTextField(
        modifier = modifier.focusRequester(focusRequester),
        value = text,
        onValueChange = {
            if (maxLength == -1) {
                onChange(it)
            } else if (it.length <= maxLength) {
                onChange(it)
            }
        },
        enabled = isEnabled,
        label = label,
        keyboardOptions = keyboardOptions ?: KeyboardOptions(),
        keyboardActions = keyboardActions ?: KeyboardActions(),
        labelContentDescription = labelContentDescription,
        errorMessage = if (errorMessage.isNullOrEmpty()) {
            null
        } else {
            errorMessage
        },
        supportingText = supportingText,
        visualTransformation = visualTransformation,

        )

}

/**
 * A custom TextField composable.
 *
 * @param value The input text to be shown in the text field.
 * @param onChange Callback that is triggered when the input service updates the text. An
 * updated text comes as a parameter of the callback.
 * @param modifier Modifier for this text field.
 * @param label The label to be displayed inside or above the text field.
 * @param isEnabled Controls the enabled state of the text field. When `false`, the text field will
 * be neither editable nor focusable, the input of the text field will not be selectable.
 * @param maxLength The maximum number of characters allowed in the text field. -1 for no limit.
 * @param keyboardOptions Software keyboard options that contains configuration such as
 * [KeyboardType] and [ImeAction].
 * @param keyboardActions When the input service emits an IME action, the corresponding callback
 * is called. Note that this IME action may be different from what you specified in
 * [KeyboardOptions.imeAction].
 * @param labelContentDescription Content description for the label.
 * @param errorMessage The error message to be displayed below the text field. If not null or empty,
 * the text field will be in an error state.
 * @param supportingText The supporting text to be displayed below the text field. This is usually
 * used to provide auxiliary information. It's displayed only if errorMessage is
null.
 * @param visualTransformation Transforms the visual representation of the input [value].
 * For example, you can use [PasswordVisualTransformation] to create a password text field.
 * @param focusRequester An optional [FocusRequester] to be used for this text field.
 */
@Composable
fun TMOTextField(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    enabled: Boolean = true,
    maxLength: Int = -1, // -1 indicates no limit
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    labelContentDescription: String? = null, // If null, label itself might be used by accessibility
    errorMessage: String? = null,
    supportingText: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    focusRequester: FocusRequester? = null // Made optional
) {
    val isError = !errorMessage.isNullOrEmpty()


    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            if (maxLength == -1 || newValue.length <= maxLength) {
                onValueChange(newValue)
            }
        },
        modifier = if (focusRequester != null) {
            modifier
                .focusRequester(focusRequester)
                .fillMaxWidth()
        } else {
            modifier.fillMaxWidth()
        },
        enabled = enabled,
        label = {
            Text(text = label,
                modifier = Modifier.semantics {
                    contentDescription = labelContentDescription ?: ""
                })
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        isError = isError,
        supportingText = {
            if (isError) {
                Text(
                    text = errorMessage ?: "", // Should not be null if isError is true
                    color = MaterialTheme.colorScheme.error
                )
            } else if (supportingText != null) {
                Text(text = supportingText)
            }
            // If both are null, no supporting text composable will be rendered by OutlinedTextField
        },
        visualTransformation = visualTransformation,
        singleLine = keyboardOptions.imeAction == androidx.compose.ui.text.input.ImeAction.Done ||
                keyboardOptions.imeAction == androidx.compose.ui.text.input.ImeAction.Go ||
                keyboardOptions.imeAction == androidx.compose.ui.text.input.ImeAction.Search ||
                keyboardOptions.imeAction == androidx.compose.ui.text.input.ImeAction.Send ||
                !value.contains("\n"), // Basic heuristic for singleLine
        // You can expose more parameters from OutlinedTextField if needed (e.g., colors, shapes)
    )

}

// --- Previews ---

@Preview(showBackground = true, name = "TMOTextField - Basic")
@Composable
fun TMOTextFieldBasicPreview() {
    MaterialTheme {
        var text by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf("") }
        TMOTextField(
            value = text,
            onValueChange = { text = it },
            label = "Username",
            modifier = Modifier.padding(16.dp)
        )
    }
}
