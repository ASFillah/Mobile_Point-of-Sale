package com.mobiledev.myshoppinglistapp.Retrofit

import com.mobiledev.myshoppinglistapp.Response.AddProdukResponse
import com.mobiledev.myshoppinglistapp.Response.DataItem
import com.mobiledev.myshoppinglistapp.Response.DeleteProdukResponse
import com.mobiledev.myshoppinglistapp.Response.EditProdukResponse
import com.mobiledev.myshoppinglistapp.Response.GetProdukResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService{

    @GET("produk")
    suspend fun  getProduk(): GetProdukResponse

    @FormUrlEncoded
    @POST("addProduk")
    suspend fun addProduk(
        @Field ("namaProduk") namaProduk: String,
        @Field ("hargaProduk") hargaProduk: String,
        @Field ("stokProduk") stokProduk: String,
        @Field ("kategoriProduk") kategoriProduk: String,
    ) : AddProdukResponse

    @PUT("produk/{id}/update")
    suspend fun editProduk(
        @Path("id") id: String,
        @Body produk: DataItem
    ): EditProdukResponse

    @DELETE("produk/{id}/delete")
    suspend fun deleteProduk(
        @Path("id") id: String
    ): DeleteProdukResponse
}