package com.mobiledev.myshoppinglistapp.data

import com.mobiledev.myshoppinglistapp.Response.DataItem
import com.mobiledev.myshoppinglistapp.Response.EditProdukResponse
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

    fun addItem(name: String, price: Int, stock: Int) {
        val newItem = DataItem(
            id = (_items.value.size + 1).toString(),
            namaProduk = name,
            hargaProduk = price,
            stokProduk = stock
        )
        _items.value = _items.value + newItem
    }

    fun editItem(updateItem: EditProdukResponse) {
        _items.value = _items.value.map { dataItem ->
            if (dataItem.id == updateItem.data?.id) {
                DataItem(
                    id = updateItem.data?.id ?: dataItem.id,
                    namaProduk = updateItem.data?.namaProduk ?: dataItem.namaProduk,
                    hargaProduk = updateItem.data?.hargaProduk ?: dataItem.hargaProduk,
                    stokProduk = updateItem.data?.stokProduk ?: dataItem.stokProduk
                )
            } else {
                dataItem
            }
        }
    }

    fun deleteItem(itemId: String) {
        _items.value = _items.value.filter { it.id != itemId }
    }
}