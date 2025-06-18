package com.example.composepoc.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.composepoc.utils.GmwPcdTypography
import com.example.composepoc.utils.Inky
import com.example.composepoc.utils.KpFont
import com.example.composepoc.utils.L_VERTICAL_SPACING
import com.example.composepoc.utils.M_CORNER_RADIUS
import com.example.composepoc.utils.M_VERTICAL_SPACING
import com.example.composepoc.utils.S_ELEVATION
import com.example.composepoc.utils.S_VERTICAL_SPACING
import com.example.composepoc.utils.TEXT_SIZE_16
import com.example.composepoc.utils.XL_VERTICAL_SPACING
import com.example.composepoc.utils.XS_ELEVATION
import com.example.composepoc.utils.XXXL_VERTICAL_SPACING
import com.example.composepoc.utils.XXXS_HORIZONTAL_SPACING
import com.example.composepoc.utils.XXXS_VERTICAL_SPACING
import kotlin.text.uppercase

@Composable
fun CustomDialog(
headerText: String,
descriptionText: String,
buttonText: String,
onDismiss: () -> Unit,
onContinueClick: () -> Unit,
) {
    val cardWidth = 0.95f
    Dialog (onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false, dismissOnBackPress = false, dismissOnClickOutside = false, ), ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(cardWidth)
                .border(
                    XXXS_VERTICAL_SPACING,
                    color = Color.White,
                    RoundedCornerShape(M_CORNER_RADIUS)
                )
                .wrapContentSize(Alignment.Center),
            elevation = CardDefaults.cardElevation(defaultElevation = S_ELEVATION),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(
                        M_VERTICAL_SPACING,
                        M_VERTICAL_SPACING,
                        M_VERTICAL_SPACING,
                        L_VERTICAL_SPACING,
                    ),
            ) {
                Spacer(modifier = Modifier.height(S_VERTICAL_SPACING))
                Text (text =
                    headerText, style = GmwPcdTypography.headlineMedium, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(), )
                Spacer(modifier = Modifier.height(S_VERTICAL_SPACING))
                Text(text = descriptionText, style = GmwPcdTypography.bodySmall, textAlign = TextAlign.Center, color = Inky, )
                Spacer(modifier = Modifier.height(XL_VERTICAL_SPACING))
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .height(height = XXXL_VERTICAL_SPACING),
                    colors = ButtonDefaults.buttonColors(Blue),
                    border = BorderStroke(width = XXXS_HORIZONTAL_SPACING, color = Blue),
                    elevation = ButtonDefaults.buttonElevation(pressedElevation = XS_ELEVATION),
                    onClick = { onContinueClick() }, ) {
                Text(
                    modifier = Modifier,
                    text = buttonText.uppercase(),
                    style = TextStyle(
                        color = White,
                        fontSize = TEXT_SIZE_16,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = KpFont,
                    ),
                )
            }
            }
        }
    }
}
