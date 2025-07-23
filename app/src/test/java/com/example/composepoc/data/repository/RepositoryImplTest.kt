package com.example.composepoc.data.repository

import com.example.composepoc.data.model.ProductListDTO
import com.example.composepoc.data.netwotk.ApiService
import com.example.composepoc.data.respository.RepositoryImpl
import com.example.composepoc.domain.model.ProductItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import org.junit.Assert.assertEquals
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class RepositoryImplTest {
    // Declare Mock and Class Under test
    private lateinit var mockApiService: ApiService
    private lateinit var repository: RepositoryImpl

    @Before
    fun setUp(){
        // Initialize Mocks and Class Under Test
        mockApiService = mock() // Creates a mock instance of ApiService
        repository = RepositoryImpl(mockApiService)
    }

    @Test
    fun `getProductList success should return mapped product list`() = runTest {

    }

    @Test
    fun getProductList_success_should_return_mapped_product_list() = runTest {
        // given
        val mockApiProductItems = listOf(ProductListDTO("abc","Hi",1, "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg","abc","Product A"))
        val expectedProductItem = listOf(ProductItem(1, "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg","Product A", "Hi"))
        whenever(mockApiService.getAllProductListAPI()).thenReturn(mockApiProductItems)

        // when
        val result = repository.getProductList()

        // then
        assertEquals(expectedProductItem, result)
        verify(mockApiService).getAllProductListAPI()
    }
}