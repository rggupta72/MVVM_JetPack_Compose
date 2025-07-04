package com.example.composepoc.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.composepoc.R

@Composable
fun practise1(onClick: (abc: String) -> Unit) {

    var textValue by remember { mutableStateOf("") }
    var checkBoxValue by remember { mutableStateOf(false) }
    var isSwitch by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Option 1") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                onClick("")
            }
            .verticalScroll(rememberScrollState())
            .scrollable(rememberScrollState(), Orientation.Vertical)
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            ConstraintLayout {
                val (text1, text2, image, button, tonalButton,
                    elevatedButton, outlineButton, textButton, textField, checkBox, switch,
                    radioGroup, progressBar, box, list) = createRefs()

                // Guidelines: Helps you create consistent and adaptive
                // layout by positioning element relative to a percentage or specific
                // distance from the start or end of the parent

                val startGuideline = createGuidelineFromStart(.15f)
                val endGuideline = createGuidelineFromEnd(.15f)

                Text(
                    text = "Hi Message",
                    color = Color.Black,
                    modifier = Modifier
                        .constrainAs(text1) {
                            top.linkTo(parent.top, margin = 10.dp)
                            start.linkTo(startGuideline)
                            bottom.linkTo(text2.top)
                        }
                        .padding(10.dp)
                        .semantics { heading() }
                        .semantics { contentDescription = "Hi Message" },
                    fontSize = 10.sp,
                    textAlign = TextAlign.Center

                )

                Text(text = "Hi Message", modifier = Modifier
                    .constrainAs(text2) {
                        top.linkTo(text1.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(image.top)
                        width = Dimension.matchParent
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
                Image(
                    modifier = Modifier
                        .constrainAs(image) {
                            top.linkTo(text2.bottom)
                            start.linkTo(startGuideline)
                            end.linkTo(parent.end)
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
                            end.linkTo(endGuideline)
                        }
                        .semantics {
                            contentDescription = ""
                        },
                    onClick = {
                        onClick("")
                    },
                ) {
                    Text(
                        "My Button", color = Color.White,
                    )
                }

                FilledTonalButton(
                    onClick = {
                        onClick("")
                    },
                    modifier = Modifier
                        .constrainAs(tonalButton) {
                            top.linkTo(button.bottom)
                            end.linkTo(endGuideline)
                        }
                        .semantics { contentDescription = "" }
                        .padding(10.dp),
                ) {
                    Text("Filled Tonal Button", color = Color.White)
                }

                ElevatedButton(
                    onClick = {
                        onClick("")
                    },
                    modifier = Modifier
                        .constrainAs(elevatedButton) {
                            top.linkTo(tonalButton.bottom)
                            start.linkTo(startGuideline)
                        }
                        .semantics { contentDescription = "" }
                        .padding(10.dp),
                ) {
                    Text("Elevated Button", color = Color.White)
                }

                OutlinedButton(onClick = {
                    onClick("")
                }, modifier = Modifier
                    .constrainAs(outlineButton) {
                        top.linkTo(elevatedButton.bottom)
                        end.linkTo(endGuideline)
                    }
                    .semantics { contentDescription = "" }) {
                    Text("Outline Text", color = Color.Black)
                }

                TextButton(onClick = {
                    onClick("")
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
                            end.linkTo(endGuideline)
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
                            end.linkTo(endGuideline)
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
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
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
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

                Box(
                    modifier = Modifier
                        .constrainAs(box) {
                            top.linkTo(progressBar.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
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