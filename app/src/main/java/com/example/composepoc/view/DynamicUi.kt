package com.example.composepoc.view

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

@Composable
fun DynamiCUi() {

    val textvalue = remember { mutableStateOf("Raje") }

    DynamicCard(
        modifier = Modifier.padding(10.dp),
        cardElevation = 5.dp,
        onClickAction = {
            // to Do
        }) {
        ConstraintLayout {
            val (headingText, mediumStartText, mediumEndText, button, textField) = createRefs()
            val startGuideLine = createGuidelineFromStart(10.dp)
            val endGuideLine = createGuidelineFromEnd(10.dp)
            val topGuideLine = createGuidelineFromTop(10.dp)
            val startGuideline = createGuidelineFromStart(0.5f)
            val bottomBarrier = createBottomBarrier(mediumStartText, mediumEndText)
//        createHorizontalChain(
//            mediumStartText, mediumEndText,
//            chainStyle = ChainStyle.SpreadInside
//        )


            DynamicText(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(headingText) {
                        start.linkTo(startGuideLine)
                        top.linkTo(topGuideLine)
                        end.linkTo(endGuideLine)
                    }
                    .semantics { heading() }
                    .semantics { contentDescription = "Dynamic Text" },
                text = "Dynamic Text",

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
                        top.linkTo(bottomBarrier)
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

            TextInputField(
                modifier = Modifier
                    .constrainAs(textField) {
                        top.linkTo(button.bottom)
                        start.linkTo(parent.start, 10.dp)
                        end.linkTo(parent.end, 10.dp)
                        width = Dimension.fillToConstraints
                    }
                    .padding(vertical = 10.dp)
                    .semantics { contentDescription = textvalue.value }, textvalue.value
            ) {
                textvalue.value = it
            }
        }


    }


}