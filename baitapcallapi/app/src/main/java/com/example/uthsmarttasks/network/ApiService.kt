package com.example.uthsmarttasks.network
import com.example.uthsmarttasks.model.Product
import retrofit2.http.GET

interface ApiService {
    @GET("v2/product")
    suspend fun getProducts(): List<Product>
}

