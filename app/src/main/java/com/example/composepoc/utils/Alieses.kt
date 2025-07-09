package com.example.composepoc.utils

import androidx.compose.runtime.Composable

typealias Content = @Composable () -> Unit
internal typealias FABAction = () -> Unit
typealias Action = () -> Unit

internal typealias IntAction = (Int) -> Unit
internal typealias StringAction = (String) -> Unit
internal typealias BooleanAction = (Boolean) -> Unit



