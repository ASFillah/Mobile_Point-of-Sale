package com.mobiledev.myshoppinglistapp.Retrofit

import com.mobiledev.myshoppinglistapp.Response.GetProdukResponse
import retrofit2.http.GET

interface ApiService{

    @GET("produk")
    suspend fun  getProduk(): GetProdukResponse
}