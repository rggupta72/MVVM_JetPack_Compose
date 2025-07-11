package com.example.composepoc.view.reuse

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composepoc.utils.IntAction


@Composable

fun TWSpinner(

    label: String,

    modifier: Modifier = Modifier,

    options: List<String>,

    preselectedIndex: Int = 0,

    preExpanded: Boolean = false,

    enabled: Boolean = true,

    onSelectionChanged: IntAction,

    ) {


    TWSpinnerWithLabel(

        label = label,

        modifier = modifier,

        options = options,

        isEnabled = enabled,

        preselectedIndex = preselectedIndex,

        onSelectionChanged = onSelectionChanged,

        )
}

@Preview
@Composable
private fun SpinnerPreview() {
    TWSpinner(
        modifier = Modifier.fillMaxWidth(),
        options = listOf("Lorem", "Ipsum", "Dolar"),
        preselectedIndex = 0,
        label = "Hi",
        onSelectionChanged = {
            
        }

    )
}


/**

 * Dropdown selection component; traditionally called Spinner

 *

 * @param label Label your Spinner

 * @param options list of dropdown menu items

 * @param preselectedIndex index of pre-selected dropdown item

 * @param modifier Modifier to change thr UI appearance

 * @param enable

 * @param labelContentDescription

 * @param supportingText Supporting text

 * @param errorMessage

 * @param onSelectionChanged action to perform on selection changes, index of

 * the item selected is passed

 * */

@Composable

fun TWSpinnerWithLabel(

    label: String,

    options: List<String>,

    preselectedIndex: Int,

    modifier: Modifier = Modifier,

    isEnabled: Boolean = true,

    contentDescription: String? = null,

    supportingText: String? = null,

    errorMessage: String? = null,

    onSelectionChanged: IntAction,

    ) {

    val selectedIndex = remember { mutableIntStateOf(preselectedIndex) }

    if (selectedIndex.intValue != preselectedIndex) {

        selectedIndex.intValue = preselectedIndex

    }

    TMOSelectField(

        label = label,

        enabled = isEnabled,

        options = options.takeIf { it.isNotEmpty() }?.mapIndexed { index, labelTxt ->

            TMOSelectFieldOption(labelTxt, selected = index == selectedIndex.intValue)

        } ?: emptyList(),

        onSelectedChange = {

                index, _ ->

            selectedIndex.intValue = index

            onSelectionChanged.invoke(index)

        },

        modifier = modifier,

        labelContentDescription = contentDescription ?: label,

        supportingText = supportingText,

        errorMessage = errorMessage,

        )

}

data class TMOSelectFieldOption(
    val label: String,
    val selected: Boolean = true,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TMOSelectField(
    label: String,
    enabled: Boolean,
    options: List<TMOSelectFieldOption>,
    onSelectedChange: (Int, TMOSelectFieldOption) -> Unit,
    modifier: Modifier = Modifier,
    labelContentDescription: String? = null,
    supportingText: String? = null,
    errorMessage: String? = null,
) {
    var expanded by remember { mutableStateOf(false) }
    val selectedIndex = options.indexOfFirst { it.selected }.takeIf { it >= 0 } ?: 0
    val selectedOption = options.getOrNull(selectedIndex)?.label ?: ""

    Column(modifier = modifier) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedOption,
                onValueChange = {},
                readOnly = true,
                label = { Text(label) },
                enabled = enabled,
                isError = errorMessage != null,
                supportingText = supportingText?.let { { Text(it) } },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEachIndexed { index, option ->
                    DropdownMenuItem(
                        text = { Text(option.label) },
                        onClick = {
                            expanded = false
                            onSelectedChange(index, option)
                        }
                    )
                }
            }
        }
        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}




