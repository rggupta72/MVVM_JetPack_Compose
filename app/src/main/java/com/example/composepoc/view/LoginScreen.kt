package com.example.composepoc.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.widget.Guideline
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composepoc.R
import com.example.composepoc.presentation.event.LoginEvent
import com.example.composepoc.presentation.viewmodel.LoginViewModel


@Composable
fun loginScreen(
    onEvent: (LoginEvent) -> Unit,
    viewModel: LoginViewModel
) {
    val context = LocalContext.current
    val result by viewModel.uiState.collectAsStateWithLifecycle()
    var userState by remember { mutableStateOf("") }
    var pwdState by remember { mutableStateOf("") }
    val sigunUpValidation = remember {
        derivedStateOf {
            userState.isNotEmpty() && pwdState.isNotEmpty()
        }
    }
    Column(modifier = Modifier.background(color = Color.Yellow.copy(alpha = .2f)).verticalScroll(
        rememberScrollState()
    )) {
        ConstraintLayout {
            val startGuideLine = createGuidelineFromStart(.15f)
            val endGuideLine = createGuidelineFromEnd(.15f)
            val topGuideLine = createGuidelineFromTop(10.dp)
            val (text, image, userName, userPwd, button) = createRefs()
            Text("Hi Raju 2",
                fontSize = 26.sp,
                modifier = Modifier
                    .constrainAs(text) {
                        top.linkTo(topGuideLine, margin = 250.dp)
                        start.linkTo(startGuideLine)
                        end.linkTo(endGuideLine)
                    }
                    .clickable { onEvent(LoginEvent.ProductList) }
                    .semantics { heading() }
                    .semantics { contentDescription = "" })

            Image(
                modifier = Modifier.constrainAs(image) {
                    top.linkTo(text.bottom, margin = 10.dp)
                    start.linkTo(startGuideLine)
                    end.linkTo(endGuideLine)
                }, painter = painterResource(R.drawable.notification_24),
                contentDescription = null
            )
            OutlinedTextField(
                modifier = Modifier.constrainAs(userName) {
                    top.linkTo(image.bottom, margin = 10.dp)
                    start.linkTo(startGuideLine)
                    end.linkTo(endGuideLine)
                },
                value = userState,
                onValueChange = {
                    userState = it
                },
                label = {
                    Text("Enter UserName")
                })

            OutlinedTextField(
                modifier = Modifier.constrainAs(userPwd) {
                    top.linkTo(userName.bottom, margin = 10.dp)
                    start.linkTo(startGuideLine)
                    end.linkTo(endGuideLine)
                },
                value = pwdState,
                onValueChange = {
                    pwdState = it
                },
                label = {
                    Text("Enter UserPwd")
                })

            Button(modifier = Modifier.constrainAs(button) {
                top.linkTo(userPwd.bottom, margin = 10.dp)
                start.linkTo(startGuideLine)
                end.linkTo(endGuideLine)
            },
                enabled = sigunUpValidation.value,
                onClick = {
                    onEvent(LoginEvent.ProductList)
                }) {
                Text("Sign Up")
            }
        }

    }
}