package com.example.composepoc.adapter

import androidx.databinding.ViewDataBinding
import com.example.composepoc.BR

class GmwHealthNeedsSectionItemViewHolder(private val binding: ViewDataBinding) :
    BaseViewHolder<HealthNeedsSectionTitleItemState>(binding) {
    override fun bindData(dataModel: HealthNeedsSectionTitleItemState) {
        with(binding) {
            setVariable(BR.gmwHubSectionTitleItemState, dataModel)
        }
    }
}