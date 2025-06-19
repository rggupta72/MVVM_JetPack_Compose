package com.example.composepoc.adapter

import androidx.recyclerview.widget.DiffUtil

internal class HealthNeedDiffUtil :
    DiffUtil.ItemCallback<BaseRecyclerViewItemState<HealthNeedsViewType>>() {
    override fun areItemsTheSame(
        oldItem: BaseRecyclerViewItemState<HealthNeedsViewType>,
        newItem: BaseRecyclerViewItemState<HealthNeedsViewType>,
    ): Boolean {
        return oldItem.viewType == newItem.viewType
    }

    override fun areContentsTheSame(
        oldItem: BaseRecyclerViewItemState<HealthNeedsViewType>,
        newItem: BaseRecyclerViewItemState<HealthNeedsViewType>,
    ): Boolean {
        return oldItem == newItem
    }
}