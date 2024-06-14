package com.mobiledev.myshoppinglistapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ShoppingListViewModel (private val repository: ShoppingListRepository) : ViewModel() {
    val items: StateFlow<List<ShoppingItem>> = repository.items

    fun addItem(name: String, price: Double, stock: Int) {
        viewModelScope.launch {
            repository.addItem(name, price, stock)
        }
    }

    fun editItem(updateItem: ShoppingItem) {
        viewModelScope.launch {
            repository.editItem(updateItem)
        }
    }

    fun deleteItem(itemId: Int) {
        viewModelScope.launch {
            repository.deleteItem(itemId)
        }
    }
}