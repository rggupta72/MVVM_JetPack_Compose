package com.example.composepoc.adapter

import com.example.composepoc.databinding.ItemHealthNeedsBinding
import com.example.composepoc.presentation.viewmodel.ProductListVewModel


internal class GmwHealthNeedsViewHolder(
    private val binding: ItemHealthNeedsBinding,
    viewModel: ProductListVewModel,
) : BaseViewHolder<HealthNeedItemViewState>(binding) {
    init {
        binding.viewModel = viewModel
    }
    override fun bindData(dataModel: HealthNeedItemViewState) {
        binding.itemState = dataModel
        binding .executePendingBindings ()
    }
}