package com.example.composepoc.view

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composepoc.presentation.event.LoginEvent
import com.example.composepoc.presentation.viewmodel.LoginViewModel


@Composable
fun loginScreen(
    onEvent: (LoginEvent) -> Unit,
    viewModel: LoginViewModel
) {
    val context = LocalContext.current
    val result by viewModel.uiState.collectAsStateWithLifecycle()
    Text("Hi Raju", modifier = Modifier.clickable { onEvent(LoginEvent.ProductList) })
}