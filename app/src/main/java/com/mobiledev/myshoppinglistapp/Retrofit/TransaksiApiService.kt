package com.mobiledev.myshoppinglistapp.Retrofit

import com.mobiledev.myshoppinglistapp.Response.TransaksiResponse.AddTransaksiResponse
import com.mobiledev.myshoppinglistapp.Response.TransaksiResponse.DeleteTransaksiResponse
import com.mobiledev.myshoppinglistapp.Response.TransaksiResponse.GetTransaksiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TransaksiApiService {

    @FormUrlEncoded
    @POST("addTransaksi")
    suspend fun addTransaksi(
        @Body requestBody: Map<String, Any>
    ): Response<AddTransaksiResponse>

    @FormUrlEncoded
    @DELETE("deleteTransaksi/{id}")
    suspend fun deleteTransaksi(@Path("id") id: String): Response<DeleteTransaksiResponse>

    @FormUrlEncoded
    @GET("transaksi")
    suspend fun getTransaksi(): Response<GetTransaksiResponse>
}