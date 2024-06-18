package com.mobiledev.myshoppinglistapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobiledev.myshoppinglistapp.Response.DataItem
import com.mobiledev.myshoppinglistapp.Response.EditProdukResponse
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ShoppingListViewModel (private val repository: ShoppingListRepository) : ViewModel() {
    val items: StateFlow<List<DataItem>> = repository.items

    fun addItem(name: String, price: Int, stock: Int) {
        viewModelScope.launch {
            repository.addItem(name, price, stock)
        }
    }

    fun editItem(updateItem: EditProdukResponse) {
        viewModelScope.launch {
            repository.editItem(updateItem)
        }
    }

    fun deleteItem(itemId: String) {
        viewModelScope.launch {
            repository.deleteItem(itemId)
        }
    }
}