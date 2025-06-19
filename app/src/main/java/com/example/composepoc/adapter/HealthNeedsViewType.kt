package com.example.composepoc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.composepoc.databinding.ItemHealthNeedsBinding
import com.example.composepoc.databinding.ItemHealthNeedsSectionHeaderBinding
import com.example.composepoc.presentation.viewmodel.ProductListVewModel

@Suppress("UNCHECKED_CAST")
enum class HealthNeedsViewType {
    GMW_HUB {
        override fun createViewHolder(
            parent: ViewGroup,
            viewModel: ProductListVewModel,
        ): BaseViewHolder<BaseRecyclerViewItemState<HealthNeedsViewType>> {
            val binding =
                ItemHealthNeedsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return GmwHealthNeedsViewHolder(
                binding,
                viewModel
            ) as BaseViewHolder<BaseRecyclerViewItemState<HealthNeedsViewType>>
        }
    },

    SECTION {
        override fun createViewHolder(
            parent: ViewGroup,
            viewModel: ProductListVewModel,
        ): BaseViewHolder<BaseRecyclerViewItemState<HealthNeedsViewType>> {
            return GmwHealthNeedsSectionItemViewHolder(
                ItemHealthNeedsSectionHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                ),
            ) as BaseViewHolder<BaseRecyclerViewItemState<HealthNeedsViewType>>
        }
    },
    ;

    abstract fun createViewHolder(
        parent: ViewGroup,
        viewModel: ProductListVewModel,
    ): BaseViewHolder<BaseRecyclerViewItemState<HealthNeedsViewType>>
}