package com.example.composepoc.view

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class) // Opt-in for this specific function
@Composable
fun MyBottomSheetScreen(isBottomSheet: Boolean, onClick:(showSheet: Boolean)->Unit) {
    var showSheet by remember { mutableStateOf(isBottomSheet) }
    val sheetState = rememberModalBottomSheetState() // Call to experimental API
    val scope = rememberCoroutineScope()

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showSheet = false
                onClick(showSheet)
                               },
            sheetState = sheetState
        ) {
            // Sheet content
            Button(onClick = {
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        showSheet = false
                        onClick(showSheet)
                    }
                }
            }) {
                Text("Hide Bottom Sheet")
            }
            // Add more content here
        }
    }
}