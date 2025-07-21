package com.example.composepoc.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.composepoc.presentation.event.CommonDataEvent
import com.example.composepoc.presentation.viewmodel.CommonViewModel
import com.example.composepoc.view.reuse.ButtonUi
import com.example.composepoc.view.reuse.DynamicCard
import com.example.composepoc.view.reuse.DynamicText
import com.example.composepoc.view.reuse.TWSpinner
import com.example.composepoc.view.reuse.TWTextField

@Composable
fun DynamiCUi(
    onEvent: (CommonDataEvent) -> Unit,
    viewModel: CommonViewModel
) {

    val textValue = remember { mutableStateOf("Raju") }
    val spinnerValue = remember { mutableStateOf(0) }

    DynamicCard(
        modifier = Modifier.padding(10.dp).fillMaxSize(),
        cardElevation = 5.dp,
        onClickAction = {
            // to Do
        }) {
        ConstraintLayout {
            val (spinner, headingText, mediumStartText, mediumEndText, button, textField) = createRefs()
            val startGuideLine = createGuidelineFromStart(10.dp)
            val endGuideLine = createGuidelineFromEnd(10.dp)
            val topGuideLine = createGuidelineFromTop(10.dp)
            val startGuideline = createGuidelineFromStart(0.5f)
            val bottomBarrier = createBottomBarrier(mediumStartText, mediumEndText)
//        createHorizontalChain(
//            mediumStartText, mediumEndText,
//            chainStyle = ChainStyle.SpreadInside
//        )

            TWTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(textField) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
                    .padding(10.dp)
                    .semantics { contentDescription = textValue.value },
                text = textValue.value,
                onChange = {
                    textValue.value = it
                },
                label = ""
            )
            TWSpinner(
                modifier = Modifier
                    .constrainAs(spinner) {
                        top.linkTo(textField.bottom, margin = 10.dp)
                        start.linkTo(parent.start, margin = 10.dp)
                        end.linkTo(parent.end, margin = 10.dp)
                        width = Dimension.fillToConstraints
                    }
                    .fillMaxWidth(),
                options = listOf("Lorem", "Ipsum", "Dolar"),
                preselectedIndex = spinnerValue.value,
                label = "Hi",
                onSelectionChanged = {
                    spinnerValue.value = it
                }

            )

            DynamicText(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(headingText) {
                        start.linkTo(startGuideLine)
                        top.linkTo(spinner.bottom)
                        end.linkTo(endGuideLine)
                    }
                    .semantics { heading() }
                    .semantics { contentDescription = "Dynamic Text" },
                text = textValue.value,

                color = Color.Black,
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )

            DynamicText(
                modifier = Modifier
                    .constrainAs(mediumStartText) {
                        top.linkTo(headingText.bottom)
                        start.linkTo(parent.start, 10.dp)
                        end.linkTo(startGuideline)
                        width = Dimension.fillToConstraints
                    },
                text = "Hi My Name is Raju Gupta",
                color = Color.Blue, textAlign = TextAlign.Center,
            )

            DynamicText(
                modifier = Modifier
                    .constrainAs(mediumEndText) {
                        top.linkTo(headingText.bottom)
                        start.linkTo(startGuideline, 10.dp)
                        end.linkTo(parent.end, 10.dp)
                        width = Dimension.fillToConstraints
                    },
                text = "I am android developer with 10 plus years of experiance",
                color = Color.Blue,
            )

            ButtonUi(
                modifier = Modifier
                    .constrainAs(button) {
                        top.linkTo(mediumEndText.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .semantics { contentDescription = "Button" },
                color = ButtonDefaults.buttonColors(Color.Red),
                "Button",
                textColour = Color.White,
                textAlign = TextAlign.Start
            ) {

            }


        }


    }


}