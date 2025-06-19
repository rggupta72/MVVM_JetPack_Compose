package com.example.composepoc.adapter

data class HealthNeedsSectionTitleItemState(val sectionName: String, val isVisible: Boolean) :
    BaseRecyclerViewItemState<HealthNeedsViewType> {
    override val viewType: HealthNeedsViewType
        get() = HealthNeedsViewType.SECTION
}