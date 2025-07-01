package com.example.composepoc.domain.usecase

import com.example.composepoc.core.common.UiState
import com.example.composepoc.data.respository.RepositoryImpl
import com.example.composepoc.domain.model.ProductItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(private val repositoryImpl: RepositoryImpl) {

    fun invoke(): Flow<UiState<List<ProductItem>>> = flow {
        emit(UiState.Loading())
        try {
            emit(UiState.Success(data = repositoryImpl.getProductList()))
        } catch (e: Exception) {
            emit(UiState.Error(message = e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getApi(): UiState<List<ProductItem>> {
        return withContext(Dispatchers.IO) {
            try {
                UiState.Success(data = repositoryImpl.getProductList())
            } catch (e: Exception) {
                UiState.Error(message = e.message.toString())
            }
        }

    }
}