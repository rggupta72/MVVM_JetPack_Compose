package com.example.composepoc.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composepoc.utils.Action
import com.example.composepoc.utils.Content

@Composable
fun DynamicCard(
    modifier: Modifier = Modifier,
    cardCornerRadius: Dp = 2.dp,
    cardElevation: Dp = 8.dp,
    cardColors: Color = Color.White,
    contentDescriptions: String? = null,
    onClickAction: Action? = null,
    borderColor: Color? = null,
    content: Content,
) {
    if (onClickAction != null) {
        Card(
            modifier = modifier.semantics {
                contentDescription = contentDescriptions ?: ""
                role = Role.Button
            },
            shape = RoundedCornerShape(corner = CornerSize(cardCornerRadius)),
            colors = CardDefaults.cardColors(containerColor = cardColors),
            elevation = CardDefaults.cardElevation(cardElevation),
            onClick = onClickAction,
            border = if (borderColor == null) null else BorderStroke(1.dp, borderColor),
        ) {
            content.invoke()
        }
    } else {
        Card(
            modifier = modifier.semantics {
                contentDescription = contentDescriptions ?: ""
                role = Role.Button
            },
            shape = RoundedCornerShape(corner = CornerSize(cardCornerRadius)),
            colors = CardDefaults.cardColors(containerColor = cardColors),
            elevation = CardDefaults.cardElevation(cardElevation),
            border = if (borderColor == null) null else BorderStroke(1.dp, borderColor),
        ) {
            content.invoke()
        }
    }
}

