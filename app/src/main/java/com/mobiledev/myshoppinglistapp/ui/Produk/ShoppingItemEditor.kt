package com.mobiledev.myshoppinglistapp.ui.Produk

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.mobiledev.myshoppinglistapp.Response.ProdukResponse.DataItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingItemEditor(item: DataItem, onEditComplete: (String, Int, Int) -> Unit) {
    var editedName by remember { mutableStateOf(item.namaProduk) }
    var editedPrice by remember { mutableStateOf(item.hargaProduk.toString()) }
    var editedStock by remember { mutableStateOf(item.stokProduk.toString()) }
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            confirmButton = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = { showDialog = false }) {
                        Text(text = "Cancel")
                    }
                    Button(onClick = {
                        val name = editedName ?: item.namaProduk ?: ""
                        val price = editedPrice.toIntOrNull() ?: item.hargaProduk ?: 0
                        val stock = editedStock.toIntOrNull() ?: item.stokProduk ?: 0
                        onEditComplete(name, price, stock)
                        showDialog = false
                    }) {
                        Text(text = "Save")
                    }
                }
            },
            title = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Edit Item")
                }
            },
            text = {
                Column {
                    editedName?.let {
                        OutlinedTextField(
                            value = it,
                            onValueChange = { editedName = it },
                            label = { Text("Name") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                capitalization = KeyboardCapitalization.Sentences
                            )
                        )
                    }
                    OutlinedTextField(
                        value = editedStock,
                        onValueChange = { editedStock = it },
                        label = { Text("Stock") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = editedPrice,
                        onValueChange = { editedPrice = it },
                        label = { Text("Price") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        )
    }
}