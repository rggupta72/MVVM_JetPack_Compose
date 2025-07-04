package com.example.composepoc.view

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composepoc.R
import com.example.composepoc.a
import com.example.composepoc.b
import com.example.composepoc.domain.DataManager
import com.example.composepoc.domain.model.DummyArray
import com.example.composepoc.presentation.viewmodel.DummyArrayViewModel
import com.example.composepoc.utils.S_ELEVATION
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
internal fun dummyUi(
    array: Array<DummyArray>,
    textClick: (abc: Int) -> Unit
) {
//    val viewModelInternal = remember { viewModel }
//    val isLoading = remember { mutableStateOf(true) }
//    val gmwTaskItem by viewModelInternal.gmwList.collectAsStateWithLifecycle()
//    val gmwErrorStatus by viewModelInternal.gmwError.collectAsStateWithLifecycle()
//    val gmwRetryState by viewModelInternal.gmwRetryState.collectAsStateWithLifecycle()
    val abc = 5
    BackHandler {
        DataManager.switchPages()
    }
    var showBottomSheetDialog by remember { mutableStateOf(false) }
    val dummyArrayViewModel: DummyArrayViewModel = hiltViewModel()
    val scrollState = rememberScrollState()
    val state = remember { mutableStateOf("") }
    var state1 = remember { mutableStateOf(::a) }
    val scope = rememberCoroutineScope()
    // Create a SnackbarHostState to control Snackbar appearances
    val snackbarHostState = remember { SnackbarHostState() }
    ConstraintLayout {

        val (text, image, button, textField, box, lazyColumn) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(text) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    height = Dimension.fillToConstraints
                }
                .clickable {
                    textClick(abc)
                },
            text = "I am tester",
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            color = Color.Cyan,
            fontSize = 16.sp

        )

        Image(
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(text.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.fillToConstraints
                }
                .clickable { state1.value = ::b },
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "Dummy Image",
            colorFilter = ColorFilter.tint(Color.Red),


            )

        Button(
            onClick = {
                showBottomSheetDialog = true
            },
            modifier = Modifier
                .constrainAs(button) {
                    top.linkTo(image.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.fillToConstraints
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
                height = Dimension.fillToConstraints
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
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                height = Dimension.fillToConstraints
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

        Column(modifier = Modifier
            .constrainAs(lazyColumn) {
                top.linkTo(box.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                height = Dimension.fillToConstraints
            }
        ) {

            LazyColumn {
                items(array) { dummyArrayItem ->
                    listItem(dummyArrayItem)
                }
            }

        }


    }
    landingScreen(state1.value)
    if (showBottomSheetDialog) {
        MyBottomSheetScreen(showBottomSheetDialog) {
            showBottomSheetDialog = it
        }
    }
}

@Composable
private fun listItem(dummyArray: DummyArray) {

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = S_ELEVATION),
        modifier = Modifier.padding(8.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {

            Image(
                imageVector = Icons.Outlined.Favorite,
                contentDescription = "",
                modifier = Modifier
                    .size(40.dp)
                    .padding(8.dp)
                    .weight(.2f)
            )

            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(.8f)
            ) {
                Text(
                    text = dummyArray.name,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                // divider
                Box(
                    modifier = Modifier
                        .background(Color(0xFFEEEEEE))
                        .fillMaxWidth(0.4f)
                        .height(2.dp)
                )
                Text(
                    dummyArray.bio,
                    color = Color.Gray
                )
            }

        }

    }

}

fun a() {
    Log.d("Demo", "The Value of A is ${5.toString()}")
}

fun b() {
    Log.d("Demo", "The Value of B is ${10.toString()}")
}

@Composable
fun landingScreen(onTimeOut: () -> Unit) {
    val currentOnTimeout by rememberUpdatedState(onTimeOut)
    LaunchedEffect(true) {
        delay(5000)
        currentOnTimeout()
    }

}