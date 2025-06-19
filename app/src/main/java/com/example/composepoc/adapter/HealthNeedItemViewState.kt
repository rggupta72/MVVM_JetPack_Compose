package com.example.composepoc.adapter


data class HealthNeedItemViewState(
    val label: String,
    val ada: String,
    val key: String,
) : BaseRecyclerViewItemState<HealthNeedsViewType> {
    override val viewType: HealthNeedsViewType
        get() = HealthNeedsViewType.GMW_HUB
}