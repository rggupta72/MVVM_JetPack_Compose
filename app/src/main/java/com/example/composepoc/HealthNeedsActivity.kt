package com.example.composepoc

import android.app.Activity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.composepoc.adapter.GmwHealthNeedsAdapter
import com.example.composepoc.databinding.ActivityHealthNeedsBinding
import com.example.composepoc.databinding.ItemHealthNeedsBinding
import com.example.composepoc.presentation.viewmodel.ProductListVewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HealthNeedsActivity : AppCompatActivity() {

    private val productListVewModel: ProductListVewModel by viewModels()

    private lateinit var binding: ActivityHealthNeedsBinding
    private lateinit var healthNeedsAdapter: GmwHealthNeedsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_health_needs)
        binding.lifecycleOwner = this@HealthNeedsActivity
        binding.recyclerView.layoutManager = LinearLayoutManager(this@HealthNeedsActivity)
        healthNeedsAdapter = GmwHealthNeedsAdapter(productListVewModel)
        binding.recyclerView.adapter = healthNeedsAdapter
        productListVewModel.getListOfHealthNeedsData()
        initObserver()
    }

    private fun initObserver() {
        val result = productListVewModel.healthNeedsList.value
        result.data.let {
            healthNeedsAdapter.submitList(it)
        }

    }

}