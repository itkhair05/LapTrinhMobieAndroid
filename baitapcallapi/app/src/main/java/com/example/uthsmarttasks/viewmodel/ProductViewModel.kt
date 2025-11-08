package com.example.uthsmarttasks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// Model dữ liệu (chỉnh lại cho đúng với JSON từ API)
data class Product(
    val id: String,
    val name: String,
    val des: String,
    val price: Double,
    val imgURL: String
)

interface ProductApiService {
    @GET("v2/product")
    suspend fun getProduct(): Product
}

class ProductViewModel : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products = _products.asStateFlow()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://mock.apidog.com/m1/890655-872447-default/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(ProductApiService::class.java)

    fun fetchProducts() {
        viewModelScope.launch {
            try {
                val product = api.getProduct() // lấy 1 sản phẩm
                _products.value = listOf(product) // bọc vào List để RecyclerView hoặc LazyColumn đọc được
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
