package com.mobiledev.myshoppinglistapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mobiledev.myshoppinglistapp.ui.Produk.ProdukViewModel
import com.mobiledev.myshoppinglistapp.data.ProdukRepository

class ShoppingListViewModelFactory(private val repository: ProdukRepository) : ViewModelProvider.Factory {
    override fun  <T : ViewModel> create(modelClass: Class<T>): T{
        if (modelClass.isAssignableFrom(ProdukViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProdukViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}