package com.example.composepoc.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberAsyncImagePainter
import com.example.composepoc.domain.model.ProductItem


@Composable
fun listItem(category: ProductItem, onItemClick: (ProductItem) -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable {
            onItemClick(category)
        }) {
        ConstraintLayout {

            val (img, userDesc) = createRefs()

            Image(
                modifier = Modifier
                    .constrainAs(img) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(userDesc.start)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                        horizontalChainWeight = 0.4f
                    }
                    .size(200.dp)
                    .padding(8.dp),
                painter = rememberAsyncImagePainter(category.image),
                contentDescription = ""
            )
            userDescription(category, Modifier
                .constrainAs(userDesc) {
                    top.linkTo(img.top)
                    end.linkTo(parent.end)
                    start.linkTo(img.end)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                    horizontalChainWeight = 0.6f
                }
            )

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp))
        }
    }
}

@Composable
fun userDescription(category: ProductItem, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(category.title, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(10.dp))
        Text(category.description, fontSize = 12.sp, maxLines = 4, overflow = TextOverflow.Ellipsis)
    }
}
