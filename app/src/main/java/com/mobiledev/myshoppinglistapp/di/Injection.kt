//package com.mobiledev.myshoppinglistapp.di
//
//import android.content.Context
//import com.mobiledev.myshoppinglistapp.data.ProdukRepository
//import com.mobiledev.myshoppinglistapp.Retrofit.ApiConfig
//
//object Injection {
//    fun provideRepository(context: Context): ProdukRepository{
//        val  apiService = ApiConfig.getApiService()
//        return ProdukRepository(apiService)
//    }
//}