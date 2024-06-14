package com.mobiledev.myshoppinglistapp

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ShoppingListRepository {
    private val _items = MutableStateFlow<List<ShoppingItem>>(emptyList())
    val items: StateFlow<List<ShoppingItem>> = _items

    fun addItem(name: String, price: Double, stock: Int) {
        val newItem = ShoppingItem(
            id = _items.value.size + 1,
            name = name,
            price = price,
            stock = stock
        )
        _items.value = _items.value + newItem
    }

    fun editItem(updateItem: ShoppingItem) {
        _items.value = _items.value.map { if (it.id == updateItem.id) updateItem else it }
    }

    fun deleteItem(itemId: Int) {
        _items.value = _items.value.filter { it.id != itemId }
    }
}