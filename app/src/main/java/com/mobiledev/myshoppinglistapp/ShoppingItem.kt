package com.mobiledev.myshoppinglistapp

data class ShoppingItem(
    val id:Int,
    var name: String,
    var price: Double,
    var stock: Int,
    var isEditing: Boolean = false
)
