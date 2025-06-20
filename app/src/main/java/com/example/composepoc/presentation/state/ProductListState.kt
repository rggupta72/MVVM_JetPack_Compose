package com.example.composepoc.presentation.state

import com.example.composepoc.adapter.BaseRecyclerViewItemState
import com.example.composepoc.adapter.HealthNeedsViewType
import com.example.composepoc.domain.model.ProductItem

data class ProductListState(
    val isLoading: Boolean = false,
    val data: List<ProductItem>? = null,
    var error: String = ""
)

data class HealthNeedsState(
    val data: List<BaseRecyclerViewItemState<HealthNeedsViewType>> = emptyList(),
)
