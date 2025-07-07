package com.example.composepoc.view

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun DynamiCUi() {

    ConstraintLayout {
        val (text) = createRefs()
        val startGuideLine = createGuidelineFromStart(10.dp)
        val endGuideLine = createGuidelineFromEnd(10.dp)
        val topGuideLine = createGuidelineFromTop(10.dp)
        val barrierEnd = createEndBarrier(text)
        val barrierStart = createStartBarrier()
        createHorizontalChain(
            text
        )

        HeadingText(modifier = Modifier.constrainAs(text) {
            start.linkTo(startGuideLine)
            top.linkTo(topGuideLine)
            end.linkTo(endGuideLine)
        }, "Dynamic Text", Color.Black, MaterialTheme.typography.headlineLarge)

    }


}