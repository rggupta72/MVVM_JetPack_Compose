package com.example.composepoc.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import com.example.composepoc.presentation.viewmodel.ProductListVewModel

internal class GmwHealthNeedsAdapter(private val viewModel: ProductListVewModel) :
    ListAdapter<BaseRecyclerViewItemState<HealthNeedsViewType>, BaseViewHolder<BaseRecyclerViewItemState<HealthNeedsViewType>>>(
        AsyncDifferConfig.Builder(HealthNeedDiffUtil()).build(),
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<BaseRecyclerViewItemState<HealthNeedsViewType>> {
        return HealthNeedsViewType.values()[viewType].createViewHolder(parent, viewModel)
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<BaseRecyclerViewItemState<HealthNeedsViewType>>,
        position: Int
    ) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType.ordinal
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}