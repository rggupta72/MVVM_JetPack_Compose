package com.example.composepoc.view

import android.text.Html
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.composepoc.R
import com.example.composepoc.data.model.GmwUiInterstitialModel
import com.example.composepoc.utils.Blue
import com.example.composepoc.utils.DeepBlue
import com.example.composepoc.utils.GMW_ERROR
import com.example.composepoc.utils.GMW_LOADING_PROFILE
import com.example.composepoc.utils.GMW_REFRESH_WELCOME_STEPS
import com.example.composepoc.utils.GmwPcdTypography
import com.example.composepoc.utils.Grey
import com.example.composepoc.utils.Inky
import com.example.composepoc.utils.L_VERTICAL_SPACING
import com.example.composepoc.utils.M_HORIZONTAL_SPACING
import com.example.composepoc.utils.M_VERTICAL_SPACING
import com.example.composepoc.utils.MidNight
import com.example.composepoc.utils.PaleBlue
import com.example.composepoc.utils.SHIMMER_GREY
import com.example.composepoc.utils.SMOKE_WHITE
import com.example.composepoc.utils.S_CORNER_RADIUS
import com.example.composepoc.utils.S_ELEVATION
import com.example.composepoc.utils.S_HORIZONTAL_SPACING
import com.example.composepoc.utils.S_VERTICAL_SPACING
import com.example.composepoc.utils.WELCOME_STEPS
import com.example.composepoc.utils.WELCOME_STEPS_LOADING
import com.example.composepoc.utils.XL_VERTICAL_SPACING
import com.example.composepoc.utils.XS_HORIZONTAL_SPACING
import com.example.composepoc.utils.XS_VERTICAL_SPACING
import com.example.composepoc.utils.XXXS_VERTICAL_SPACING

@Composable
fun GmwInterstitialUi(
    gmwUiModel: GmwUiInterstitialModel,
    onContinueClick: () -> Unit,
    isLoadingStatus: Boolean,
    gmwErrorStatus: Boolean,
    onRetryClick: () -> Unit,
    onSkipClick: () -> Unit,
    isFromMyChart: Boolean,
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    Column(
        Modifier
            .fillMaxHeight()
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier
                .weight(1f, false)
                .background(Color.White)
                .verticalScroll(scrollState)
                .scrollable(scrollState, Orientation.Horizontal),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(420.dp)
                    .padding(0.dp),
                contentAlignment = Alignment.TopCenter,
            ) {
                Column() {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .semantics { contentDescription = gmwUiModel.imageAccessLabel },
                        painter = painterResource(R.drawable.ic_launcher_background),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            M_VERTICAL_SPACING,
                            XL_VERTICAL_SPACING,
                            M_VERTICAL_SPACING,
                            M_VERTICAL_SPACING,
                        ),
                ) {
                    Text(
                        text = gmwUiModel.title,
                        style = GmwPcdTypography.headlineMedium,
                        color = Inky,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusable(true)
                            .semantics { heading() },
                    )
                    Spacer(modifier = Modifier.height(M_VERTICAL_SPACING))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                XXXS_VERTICAL_SPACING,
                                color = Blue,
                                RoundedCornerShape(S_CORNER_RADIUS)
                            )
                            .wrapContentSize(Alignment.Center),
                        elevation = CardDefaults.cardElevation(defaultElevation = S_ELEVATION),
                    ) {
                        if (isLoadingStatus) {
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
                                Text(
                                    text = "",
                                    style = GmwPcdTypography.headlineMedium,
                                    color = Inky,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .focusable(true)
                                        .testTag(WELCOME_STEPS_LOADING),
                                )
                                Spacer(
                                    modifier =
                                    Modifier.height(XS_VERTICAL_SPACING)
                                )
                                Text(
                                    text = "Loading",
                                    style = GmwPcdTypography.headlineMedium,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .focusable(true)
                                        .testTag(GMW_LOADING_PROFILE),
                                    color = Color(SHIMMER_GREY),
                                )
                                Spacer(
                                    modifier =
                                    Modifier.height(M_VERTICAL_SPACING)
                                )
                                ShimmerCard()
                            }
                        } else {
                            if (gmwErrorStatus) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color.White)
                                        .padding(M_HORIZONTAL_SPACING, 0.dp, 0.dp, 0.dp),
                                ) {
                                    Image(
                                        modifier = Modifier
                                            .focusable(true)
                                            .weight(0.2f, false)
                                            .padding(0.dp, M_HORIZONTAL_SPACING, 0.dp, 0.dp)
                                            .semantics {
                                                contentDescription = gmwUiModel.iconAccessLabel
                                            },
                                        alignment = Alignment.TopStart,
                                        painter = painterResource(R.drawable.ic_launcher_background),
                                        contentDescription = null,
                                    )
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(1f, false)
                                            .background(Color.White)
                                            .padding(
                                                M_VERTICAL_SPACING,
                                                M_VERTICAL_SPACING,
                                                M_VERTICAL_SPACING,
                                                L_VERTICAL_SPACING,
                                            ),
                                    ) {
                                        Text(
                                            text = "",
                                            style = GmwPcdTypography.headlineMedium,
                                            color = Inky,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .focusable(true)
                                                .testTag(WELCOME_STEPS),
                                        )
                                        Spacer(modifier = Modifier.height(S_VERTICAL_SPACING))
                                        Row {
                                            Image(
                                                modifier = Modifier
                                                    .focusable(true)
                                                    .padding(
                                                        0.dp,
                                                        XS_HORIZONTAL_SPACING,
                                                        XS_HORIZONTAL_SPACING,
                                                        0.dp,
                                                    )
                                                    .semantics {
                                                        contentDescription =
                                                            ""
                                                    },
                                                alignment = Alignment.TopStart,
                                                painter = painterResource(R.drawable.ic_launcher_background),
                                                contentDescription = null,
                                            )
                                            Text(
                                                text =
                                                "Error",
                                                modifier = Modifier
                                                    .focusable(true)
                                                    .testTag(GMW_ERROR),
                                                textAlign = TextAlign.Start,
                                                style = GmwPcdTypography.bodySmall, color = Inky,
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(S_VERTICAL_SPACING))
                                        Row {
                                            Text(
                                                text = "Refresh Button",
                                                style = GmwPcdTypography.bodySmall,
                                                color = Blue,
                                                modifier = Modifier
                                                    .focusable(true)
                                                    .clickable(onClick = { onRetryClick() })
                                                    .testTag(GMW_REFRESH_WELCOME_STEPS),
                                            )
                                            Image(
                                                modifier = Modifier
                                                    .focusable(true)
                                                    .padding(
                                                        XS_HORIZONTAL_SPACING,
                                                        0.dp,
                                                        0.dp,
                                                        0.dp
                                                    )
                                                    .clickable(onClick = { onRetryClick() })
                                                    .testTag(GMW_REFRESH_WELCOME_STEPS)
                                                    .semantics {
                                                        contentDescription =
                                                            ""
                                                    },
                                                alignment = Alignment.TopStart,
                                                painter = painterResource(R.drawable.ic_launcher_background),
                                                contentDescription = null,
                                            )
                                        }
                                    }
                                }
                            } else {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color.White)
                                        .padding(M_HORIZONTAL_SPACING, 0.dp, 0.dp, 0.dp),
                                ) {
                                    Image(
                                        modifier = Modifier
                                            .focusable(true)
                                            .weight(0.2f, false)
                                            .padding(0.dp, M_HORIZONTAL_SPACING, 0.dp, 0.dp)
                                            .semantics {
                                                contentDescription = gmwUiModel.iconAccessLabel
                                            },
                                        alignment = Alignment.TopStart,
                                        painter = painterResource(R.drawable.ic_launcher_background),
                                        contentDescription = null,
                                    )
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(1f, false)
                                            .padding(
                                                M_VERTICAL_SPACING,
                                                M_VERTICAL_SPACING,
                                                M_VERTICAL_SPACING,
                                                L_VERTICAL_SPACING,
                                            )
                                            .clickable(onClick = { onContinueClick() }),
                                    ) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically,
                                        ) {
                                            Text(
                                                text = gmwUiModel.header,
                                                textAlign = TextAlign.Start,
                                                color = Inky,
                                                style = GmwPcdTypography.headlineMedium,
                                                modifier = Modifier.focusable(true),
                                            )
                                        }
                                        Spacer(
                                            modifier =
                                            Modifier.height(XS_VERTICAL_SPACING)
                                        )
                                        Text(
                                            text =
                                            gmwUiModel.description,
                                            style = GmwPcdTypography.bodySmall,
                                            color = Inky,
                                            modifier = Modifier.focusable(true),
                                        )
                                        if (!gmwUiModel.closeButtonVisibility) {
                                            Spacer(modifier = Modifier.height(S_VERTICAL_SPACING))
                                            Text(
                                                modifier =
                                                Modifier
                                                    .fillMaxWidth()
                                                    .focusable(true),
                                                text = (Html.fromHtml(
                                                    formatString(
                                                        gmwUiModel.taskHeader,
                                                        null,
                                                        gmwUiModel.getCurrentProgressText(),
                                                    ),
                                                    Html.FROM_HTML_MODE_LEGACY,
                                                ).toString()),
                                                style = GmwPcdTypography.bodySmall,
                                                color = Grey,
                                            )
                                            Spacer(
                                                modifier =
                                                Modifier.height(S_VERTICAL_SPACING)
                                            )
                                            RoundedProgressIndicator(
                                                gmwUiModel.currentProgress,
                                                gmwUiModel.maxListSize,
                                            )
                                        }
                                    }
                                    Image(
                                        modifier = Modifier
                                            .weight(0.1f, false)
                                            .padding(
                                                S_HORIZONTAL_SPACING,
                                                L_VERTICAL_SPACING,
                                                0.dp,
                                                0.dp
                                            )
                                            .clickable(onClick = { onContinueClick() })
                                            .semantics {
                                                contentDescription =
                                                    ""
                                            },
                                        alignment = Alignment.Center,
                                        painter = painterResource(R.drawable.ic_launcher_background),
                                        contentDescription = null,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        Button(
            onClick = { onSkipClick() },
            modifier = Modifier
                .wrapContentSize()
                .focusable(true)
                .padding(0.dp, M_VERTICAL_SPACING, 0.dp, M_VERTICAL_SPACING)
                .align(Alignment.CenterHorizontally),
            colors =
            ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        ) {
            Text(
                text = if (isFromMyChart) {
                    gmwUiModel.closeButtonLabel
                } else {
                    gmwUiModel.skipButtonLabel
                },
                style = GmwPcdTypography.headlineMedium,
                color = Blue,
                textAlign = TextAlign.Center,
            )
        }
    }
}

private fun formatString(text: String, textArg: Any?, textArgs: IntArray?): String {
    val textTypeArgs = textArgs?.toTypedArray()
    return if (textTypeArgs.isNullOrEmpty()) {
        String.format(text, textArg ?: "")
    } else {
        String.format(text, *textTypeArgs)
    }
}

fun Modifier.shimmerEffect(shape: Shape = RectangleShape): Modifier = composed {
     val INITIAL_VALUE = -2f
     val TARGET_VALUE = 2f
     val ANIMATION_DURATION = 1000
    var size by remember { mutableStateOf(IntSize.Zero) }
    val transition = rememberInfiniteTransition(label = "shimmerEffectTransition")
    val startOffsetX by transition.animateFloat(
        initialValue = INITIAL_VALUE,
        targetValue = TARGET_VALUE,
        animationSpec = infiniteRepeatable(
            animation = tween(
                ANIMATION_DURATION,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart,
        ),
        label = "shimmerEffectAnimation",
    )
    val brush = Brush.linearGradient(
        colors = listOf(
            Color(SMOKE_WHITE),
            Color(SHIMMER_GREY),
            Color(SMOKE_WHITE),
        ),
        start = Offset(startOffsetX * size.width.toFloat(), 0f),
        end = Offset((startOffsetX + 1f) * size.width.toFloat(), size.height.toFloat()),
    )
    this
        .background(
            brush = if (size != IntSize.Zero) {
                brush
            } else {
                Brush.linearGradient(colors = listOf(Color(SMOKE_WHITE), Color(SMOKE_WHITE)))
            },
            shape = shape,
        )
        .onGloballyPositioned { size = it.size }
}

@Composable
fun ShimmerCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .shimmerEffect(),
        shape = RectangleShape,
    ) { Column(modifier = Modifier.padding(8.dp)) { } }
}

@Composable
fun RoundedProgressIndicator(completedTasks: Int, totalTasks: Int) {
    val borderColor: Color = MidNight
    val animationDuration = 1000
    val cornerRadius = 10f
    val borderWidth = 2f
    val backgroundColor: Color = PaleBlue
    val progressColor: Color = DeepBlue
    val progress = if (totalTasks > 0) {
        completedTasks.toFloat() / totalTasks.toFloat()
    } else {
        0f
    }
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = animationDuration),
        label = "",
    )
    Box(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 20.dp)
            .height(8.dp)
            .focusable(true)
            .semantics {
                progressBarRangeInfo = androidx.compose.ui.semantics.ProgressBarRangeInfo(
                    current = completedTasks.toFloat(),
                    range = 0f..totalTasks.toFloat(),
                )
            },
        contentAlignment = Alignment.CenterStart,
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
        ) {
            // Background
            drawRoundRect(
                color = backgroundColor,
                cornerRadius = CornerRadius(cornerRadius, cornerRadius),
            )
            // Border
            drawRoundRect(
                color = borderColor,
                cornerRadius = CornerRadius(cornerRadius, cornerRadius),
                style = Stroke(width = borderWidth),
            )
// Progress
            drawRoundRect(
                color = progressColor,
                size = size.copy(width = size.width * animatedProgress),
                cornerRadius = CornerRadius(cornerRadius, cornerRadius),
            )
        }
    }
}