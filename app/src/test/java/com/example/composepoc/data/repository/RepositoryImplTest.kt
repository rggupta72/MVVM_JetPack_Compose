package com.example.composepoc.data.repository

import com.example.composepoc.data.model.ProductListDTO
import com.example.composepoc.data.netwotk.ApiService
import com.example.composepoc.data.respository.RepositoryImpl
import com.example.composepoc.domain.model.ProductDetail
import com.example.composepoc.domain.model.ProductItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class RepositoryImplTest {
    // Declare Mock and Class Under test
    private lateinit var mockApiService: ApiService
    private lateinit var repository: RepositoryImpl

    @Before
    fun setUp() {
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
        val mockApiProductItems = listOf(
            ProductListDTO(
                "abc",
                "Hi",
                1,
                "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                "abc",
                "Product A"
            )
        )
        val expectedProductItem = listOf(
            ProductItem(
                1,
                "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                "Product A",
                "Hi"
            )
        )
        whenever(mockApiService.getAllProductListAPI()).thenReturn(mockApiProductItems)

        // when
        val result = repository.getProductList()

        // then
        assertEquals(expectedProductItem, result)
        verify(mockApiService).getAllProductListAPI()
    }

    @Test(expected = RuntimeException::class) // Example for testing exception propagation
    fun `getProductList when API throws exception should propagate exception`() = runTest {
        // Arrange
        val errorMessage = "Network Error"
        whenever(mockApiService.getAllProductListAPI()).thenThrow(RuntimeException(errorMessage))

        // Act
        val result = repository.getProductList()

        verify(mockApiService).getAllProductListAPI()
        assertEquals(errorMessage, result)
    }

    @Test
    fun `when getProductDetails api call pass id then verify product details `() = runTest {
        // given
        val mockProductDetails = ProductListDTO(
            "abc",
            "Hi",
            1,
            "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            "abc",
            "Product A"
        )
        val exceptedProductDetails = ProductDetail(
            "abc",
            "Hi",
            1,
            "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            "abc",
            "Product A"
        )
        whenever(mockApiService.getProductDetailsAPI("1")).thenReturn(mockProductDetails)

        // when
        val result = repository.getProductDetail("1")

        // verify
        verify(mockApiService).getProductDetailsAPI("1")
        assertEquals(exceptedProductDetails, result)
    }

    @Test(expected = RuntimeException::class)
    fun `when getProductDetails Api calls throw exception`() = runTest {
        // given
        val networkErrorMessage = "Network Error"
        whenever(repository.getProductDetail("1")).thenThrow(RuntimeException(networkErrorMessage))

        // when
        val result = repository.getProductDetail("1")

        // verify
        verify(mockApiService).getProductDetailsAPI("1")
        assertEquals(networkErrorMessage, result)
    }

    @Test
    fun `when getProductList Api called then verify result`() {
        // given
        val mockProductListDTO = listOf(
            ProductListDTO(
                "abc",
                "Hi",
                1,
                "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                "abc",
                "Product A"
            )
        )
        val expectedProductItemList = listOf(
            ProductItem(
                1,
                "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                "Product A",
                "Hi"
            )
        )
        whenever(mockApiService.getAllProductListAPI1()).thenReturn(mockProductListDTO)

        // when
        val result = repository.getProductList1()

        // verify
        verify(mockApiService).getAllProductListAPI1()
        assertEquals(expectedProductItemList, result)
    }

}