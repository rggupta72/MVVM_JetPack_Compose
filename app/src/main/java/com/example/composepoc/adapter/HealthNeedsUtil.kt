package com.example.composepoc.adapter


fun getGmwHubViewStateList(gmwHubViewStateList: List<HealthNeedItemViewState>): List<BaseRecyclerViewItemState<HealthNeedsViewType>> {
    val gmwHeaderArray = gmwHubViewStateList.map { it.key }.toSet()
    val gmwCardList = gmwHeaderArray.map {
        if (it.isNotEmpty()) {
            listOf<BaseRecyclerViewItemState<HealthNeedsViewType>>(
                HealthNeedsSectionTitleItemState(it, true),
                ) +
                    gmwHubViewStateList.filter { gmwHubViewState -> gmwHubViewState.key == it }
        } else {
            gmwHubViewStateList.filter { gmwHubViewState -> gmwHubViewState.key == "" }
        }
    }.flatten()
    return gmwCardList
}