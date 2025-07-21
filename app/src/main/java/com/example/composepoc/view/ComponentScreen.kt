package com.example.composepoc.view

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.composepoc.R
import com.example.composepoc.presentation.event.ProductDetailsEvent
import com.example.composepoc.presentation.viewmodel.ProductListVewModel

@Composable
fun practise1(
    onEvent: (ProductDetailsEvent) -> Unit,
    viewModel: ProductListVewModel,
    args: Bundle?,
) {

    viewModel.updateArgsBundle(args)
    var textValue by remember { mutableStateOf("") }
    var checkBoxValue by remember { mutableStateOf(false) }
    var isSwitch by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Option 1") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                onEvent(ProductDetailsEvent.PractiseUi)
            }
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                val (text1, text2, image, button, tonalButton,
                    elevatedButton, outlineButton, textButton, textField, checkBox, switch,
                    radioGroup, progressBar, box, list) = createRefs()

                val (divider) = createRefs()

                // Guidelines: Helps you create consistent and adaptive
                // layout by positioning element relative to a percentage or specific
                // distance from the start or end of the parent

                val startGuideline = createGuidelineFromStart(.15f)
                val endGuideline = createGuidelineFromEnd(.15f)
                val topGuideline = createGuidelineFromTop(10.dp)

                // Barriers: Used to create constraints relative to the
                // edge of the multiple elements

                val barrier1 = createEndBarrier(text1, text2)

                createHorizontalChain(
                    text1, text2,
                    chainStyle = ChainStyle.Spread
                )


                dynamictextView(Modifier
                    .constrainAs(text1) {
                        top.linkTo(parent.top, margin = 10.dp)
                    })

                Text(text = "Hi I am Raju", modifier = Modifier
                    .constrainAs(text2) {
                        top.linkTo(parent.top, margin = 10.dp)

                    }
                    .padding(10.dp)
                    .semantics {
                        heading()
                    }
                    .semantics { contentDescription = "Hi message" },
                    fontSize = 10.sp,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontFamily.Serif,
                    textAlign = TextAlign.Center
                )

                HorizontalDivider(modifier = Modifier.constrainAs(divider) {
                    top.linkTo(text2.bottom, margin = 10.dp)
                    start.linkTo(parent.start, 10.dp)
                    end.linkTo(parent.end, 10.dp)
                    width = Dimension.fillToConstraints
                })
                Image(
                    modifier = Modifier
                        .constrainAs(image) {
                            top.linkTo(divider.bottom)
                            start.linkTo(barrier1)
                            bottom.linkTo(button.top)
                        }
                        .padding(10.dp)
                        .width(30.dp)
                        .height(30.dp),
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = "",
                    alignment = Alignment.Center,
                )
                Button(
                    modifier = Modifier
                        .constrainAs(button) {
                            top.linkTo(image.bottom)
                            start.linkTo(startGuideline)
                        }
                        .semantics {
                            contentDescription = ""
                        },
                    onClick = {
                        onEvent(ProductDetailsEvent.PractiseUi)
                    },
                ) {
                    Text(
                        "My Button", color = Color.White,
                    )
                }

                FilledTonalButton(
                    onClick = {
                        onEvent(ProductDetailsEvent.PractiseUi)
                    },
                    modifier = Modifier
                        .constrainAs(tonalButton) {
                            top.linkTo(button.bottom)
                            start.linkTo(startGuideline)
                        }
                        .semantics { contentDescription = "" }
                ) {
                    Text("Filled Tonal Button", color = Color.White)
                }

                ElevatedButton(
                    onClick = {
                        onEvent(ProductDetailsEvent.PractiseUi)
                    },
                    modifier = Modifier
                        .constrainAs(elevatedButton) {
                            top.linkTo(tonalButton.bottom)
                            start.linkTo(startGuideline)
                        }
                        .semantics { contentDescription = "" }
                ) {
                    Text("Elevated Button", color = Color.White)
                }

                OutlinedButton(onClick = {
                    onEvent(ProductDetailsEvent.PractiseUi)
                }, modifier = Modifier
                    .constrainAs(outlineButton) {
                        top.linkTo(elevatedButton.bottom)
                        start.linkTo(startGuideline)
                    }
                    .semantics { contentDescription = "" }) {
                    Text("Outline Text", color = Color.Black)
                }

                TextButton(onClick = {
                    onEvent(ProductDetailsEvent.PractiseUi)
                }, modifier = Modifier
                    .constrainAs(textButton) {
                        top.linkTo(outlineButton.bottom)
                        start.linkTo(startGuideline)
                    }
                    .semantics { contentDescription = "" }) {
                    Text("Text Button", color = Color.Blue)
                }

                TextField(
                    modifier = Modifier
                        .constrainAs(textField) {
                            top.linkTo(textButton.bottom)
                            start.linkTo(startGuideline)
                        }
                        .padding(10.dp)
                        .semantics {
                            contentDescription = ""
                        }
                        .semantics {
                            contentDescription = textValue
                        },
                    onValueChange = {
                        textValue = it
                    },
                    textStyle = TextStyle(Color.White),
                    value = textValue,
                    placeholder = {
                        Text(" Please Enter name")
                    },
                    label = {
                        Text("Please Enter name")
                    }


                )

                Checkbox(
                    modifier = Modifier
                        .constrainAs(checkBox) {
                            top.linkTo(textField.bottom)
                            start.linkTo(startGuideline)
                        }
                        .semantics {
                            contentDescription = "Please check check box"
                        },
                    checked = checkBoxValue,
                    onCheckedChange = { onCheckChange ->
                        checkBoxValue = onCheckChange
                    },
                    colors = CheckboxDefaults.colors(Color.Blue)
                )

                Switch(
                    modifier = Modifier
                        .constrainAs(switch) {
                            top.linkTo(checkBox.bottom)
                            start.linkTo(startGuideline)
                        }
                        .padding(10.dp)
                        .semantics { contentDescription = "Please select switch" },
                    checked = isSwitch,
                    onCheckedChange = {
                        isSwitch = it
                        checkBoxValue = it
                    }
                )

                Row(
                    modifier = Modifier
                        .constrainAs(radioGroup) {
                            top.linkTo(switch.bottom)
                            end.linkTo(endGuideline)
                        }
                        .padding(10.dp)
                ) {

                    RadioButtonText(
                        "Option 1",
                        selectedOption == "Option 1"
                    ) { value ->
                        selectedOption = "Option 1"

                    }

                    RadioButtonText(
                        "Option 2",
                        selectedOption == "Option 2"
                    ) { value ->
                        selectedOption = "Option 2"

                    }

                    RadioButtonText(
                        "Option 3",
                        selectedOption == "Option 3"
                    ) { value ->
                        selectedOption = "Option 3"

                    }


                }

                CircularProgressIndicator(modifier = Modifier.constrainAs(progressBar) {
                    top.linkTo(radioGroup.bottom)
                    end.linkTo(endGuideline)
                })

                Box(
                    modifier = Modifier
                        .constrainAs(box) {
                            top.linkTo(progressBar.bottom)
                            end.linkTo(endGuideline)
                        }
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_launcher_background),
                        contentDescription = "", modifier = Modifier
                            .width(40.dp)
                            .height(40.dp),
                        alignment = Alignment.Center
                    )
                    Image(
                        painter = painterResource(R.drawable.notification_24),
                        contentDescription = "", modifier = Modifier
                            .height(20.dp)
                            .width(20.dp),
                        alignment = Alignment.Center
                    )
                }


            }
        }

    }
}

@Composable
fun lazyColumnHeader() {

    val list = listOf("First1", "First2", "First3", "First4", "First5")
    LazyColumn {

        items(list) {
            Text(it)
        }
    }
}


@Composable
private fun RadioButtonText(
    selectedOption: String,
    isSelected: Boolean,
    selectedClick: (selectedOption: String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(selectedOption)
        RadioButton(
            selected = isSelected,
            onClick = {
                selectedClick(selectedOption)
            }
        )

    }
}

@Composable
fun dynamictextView(modifier: Modifier) {
    Text(
        text = "Hi Message",
        color = Color.Black,
        modifier = modifier
            .padding(10.dp)
            .semantics { heading() }
            .semantics { contentDescription = "Hi Message" },
        fontSize = 10.sp,
        textAlign = TextAlign.Center

    )
}