package com.example.composepoc

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composepoc.utils.S_ELEVATION

@Composable
internal fun dummyUi(
    textClick: () -> Unit
) {
//    val viewModelInternal = remember { viewModel }
//    val isLoading = remember { mutableStateOf(true) }
//    val gmwTaskItem by viewModelInternal.gmwList.collectAsStateWithLifecycle()
//    val gmwErrorStatus by viewModelInternal.gmwError.collectAsStateWithLifecycle()
//    val gmwRetryState by viewModelInternal.gmwRetryState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()
    val state = remember { mutableStateOf("") }
    ConstraintLayout {

        val (text, image, button, textField, box, lazyColumn) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(text) {
                    top.linkTo(parent.top)
                    bottom.linkTo(image.top)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
                .clickable {
                    textClick()
                },
            text = "I am tester",
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            color = Color.Cyan,
            fontSize = 16.sp

        )

        Image(
            modifier = Modifier.constrainAs(image) {
                top.linkTo(text.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "Dummy Image",
            colorFilter = ColorFilter.tint(Color.Red)
        )

        Button(
            onClick = {
                textClick()
            },
            modifier = Modifier
                .constrainAs(button) {
                    top.linkTo(image.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(10.dp)
                .wrapContentSize(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.White,
            )

        ) {

            ConstraintLayout {
                val (text, image) = createRefs()
                Text(
                    modifier = Modifier.constrainAs(text) {
                        start.linkTo(parent.start)
                    },
                    text = "Button"
                )
                Image(
                    modifier = Modifier.constrainAs(image) {
                        start.linkTo(text.end)
                    },
                    painter = painterResource(R.drawable.job_24),
                    contentDescription = "Dummy Image",
                    colorFilter = ColorFilter.tint(Color.Black)
                )
            }


        }

        TextField(
            modifier = Modifier.constrainAs(textField) {
                top.linkTo(button.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            value = state.value,
            onValueChange = {
                state.value = it
            },
            label = { Text("Enter Message") },
            placeholder = { Text("Enter Field") }

        )

        Box(modifier = Modifier
            .constrainAs(box) {
                top.linkTo(textField.bottom)
                start.linkTo(textField.start)
                end.linkTo(textField.end)
            }
            .padding(10.dp)) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.DarkGray, CircleShape)
            )
            Image(
                painter = painterResource(R.drawable.job_24),
                contentDescription = ""
            )
        }

        LazyColumn(
            modifier = Modifier
                .constrainAs(lazyColumn) {
                    top.linkTo(box.bottom)
                    start.linkTo(box.start)
                    end.linkTo(box.end)
                },
        ) {
            items(6) {
                listItem()
            }
        }


    }
}

@Preview
@Composable
private fun listItem() {

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = S_ELEVATION),
        modifier = Modifier.padding(8.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {

            Image(
                painter = painterResource(R.drawable.job_24),
                contentDescription = "",
                modifier = Modifier
                    .size(40.dp)
                    .padding(8.dp)
            )

            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    "Heading",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "Description",
                    color = Color.Gray
                )
            }

        }

    }

}