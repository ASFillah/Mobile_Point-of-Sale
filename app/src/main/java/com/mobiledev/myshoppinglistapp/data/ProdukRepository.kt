package com.mobiledev.myshoppinglistapp.data

import com.mobiledev.myshoppinglistapp.Response.DataItem
import com.mobiledev.myshoppinglistapp.Retrofit.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProdukRepository (
    val apiService: ApiService
) {

    private val _items = MutableStateFlow<List<DataItem>>(emptyList())
    val items: StateFlow<List<DataItem>> = _items

    fun setItems(newItems: List<DataItem>) {
        _items.value = newItems
    }

    suspend fun addItem(name: String, price: Int, stock: Int, kategori: String): ResultState<Unit> {
        return try {
            val response = apiService.addProduk(name, price.toString(), stock.toString(), kategori)
            if (response.error == false) {
                ResultState.Success(Unit)
            } else {
                ResultState.Error("Failed to add item: ${response.message}")
            }

        } catch (e: Exception) {
            ResultState.Error("Failed to add item: ${e.message}")
        }
    }

    suspend fun editItem(id: String, produk: DataItem): ResultState<Unit> {
        return try {
            apiService.editProduk(id, produk)
            ResultState.Success(Unit)
        } catch (e: Exception) {
            ResultState.Error("Failed to edit item: ${e.message}")
        }
    }

    suspend fun deleteItem(id: String): ResultState<Unit> {
        return try {
            apiService.deleteProduk(id)
            ResultState.Success(Unit)
        } catch (e: Exception) {
            ResultState.Error("Failed to delete item: ${e.message}")
        }
    }
}