package com.example.composepoc

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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

@Composable
fun practise1(onClick: (abc: String) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick("")
            }
            .border(6.dp, color = Color.Red)
            .border(6.dp, color = Color.Blue)
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .border(3.dp, Color.Blue)
        ) {
            ConstraintLayout {
                val (text1, text2, image, button, tonalButton,
                    elevatedButton, outlineButton, textButton, textField) = createRefs()

                Text(
                    text = "Hi Message",
                    color = Color.Black,
                    modifier = Modifier
                        .constrainAs(text1) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(text2.top)
                            width = Dimension.matchParent
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
                            start.linkTo(parent.start)
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
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
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
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
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
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
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
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .semantics { contentDescription = "" }) {
                    Text("Outline Text", color = Color.Black)
                }

                TextButton(onClick = {
                    onClick("")
                }, modifier = Modifier
                    .constrainAs(textButton) {
                        top.linkTo(outlineButton.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .semantics { contentDescription = "" }) {
                    Text("Text Button", color = Color.Blue)
                }

                TextField(
                    modifier = Modifier
                        .constrainAs(textField) {
                            top.linkTo(textButton.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .padding(10.dp)
                        .semantics {
                            contentDescription = ""
                        },
                    onValueChange = {
                        onClick(it)
                    },
                    textStyle = TextStyle(Color.White),
                    value = "Hi",
                    placeholder = {
                        Text(" Please Enter name")
                    },
                    label = {
                        Text("Please Enter name")
                    }

                )


            }
        }

    }
}