package com.mobiledev.myshoppinglistapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ShoppingListViewModelFactory(private val repository: ShoppingListRepository) : ViewModelProvider.Factory {
    override fun  <T : ViewModel> create(modelClass: Class<T>): T{
        if (modelClass.isAssignableFrom(ShoppingListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ShoppingListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}