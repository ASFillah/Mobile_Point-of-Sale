package com.mobiledev.myshoppinglistapp.ui.Produk

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.mobiledev.myshoppinglistapp.Response.ProdukResponse.DataItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListApp(viewModel: ProdukViewModel) {
    val sItems by viewModel.items.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }
    var itemName by remember { mutableStateOf("") }
    var itemStock by remember { mutableStateOf("") }
    var itemPrice by remember { mutableStateOf("") }
    var selectedItem by remember { mutableStateOf<DataItem?>(null) }
    val categories = arrayOf("Makanan", "Minuman")
    var expanded by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf("") }
//    var showSuccessDialog by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                showAddDialog = true
                selectedItem = null
                itemName = ""
                itemStock = ""
                itemPrice = ""
                selectedCategory = ""
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Add Item")
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(sItems) { item ->
                ShoppingListItem(
                    item = item,
                    onEditClick = {
                        selectedItem = item
                        itemName = item.namaProduk.toString()
                        itemStock = item.stokProduk.toString()
                        itemPrice = item.hargaProduk.toString()
                        selectedCategory = item.kategoriProduk ?: ""
                        showEditDialog = true
                    },
                    onDeleteClick = {
                        coroutineScope.launch {
                            viewModel.deleteItem(item.id ?: "")
                        }
                    }
                )
            }
        }
    }

    if (showAddDialog) {
        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            confirmButton = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = { showAddDialog = false }) {
                        Text(text = "Cancel")
                    }
                    Button(onClick = {
                        if (itemName.isNotBlank() && itemPrice.isNotBlank() && itemStock.isNotBlank() && selectedCategory.isNotBlank()) {
                            coroutineScope.launch {
                                viewModel.addItem(
                                    name = itemName,
                                    price = itemPrice.toInt(),
                                    stock = itemStock.toInt(),
                                    category = selectedCategory
                                )
                            }
                            showAddDialog = false
                        }
                    }) {
                        Text(text = "Add")
                    }
                }
            },
            title = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Add Produk")
                }
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = itemName,
                        onValueChange = { itemName = it },
                        label = { Text("Name") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            capitalization = KeyboardCapitalization.Sentences
                        )
                    )
                    OutlinedTextField(
                        value = itemStock,
                        onValueChange = { itemStock = it },
                        label = { Text("Stock") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = itemPrice,
                        onValueChange = { itemPrice = it },
                        label = { Text("Price") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = {
                            expanded = !expanded
                        }
                    ) {
                        TextField(
                            value = selectedCategory,
                            onValueChange = {},
                            readOnly = true,
                            placeholder = { Text("Category") },
                            trailingIcon = {ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                            modifier = Modifier.menuAnchor()
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            categories.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item) },
                                    onClick = {
                                        selectedCategory = item
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                }
            }
        )
    }


    if (showEditDialog) {
        AlertDialog(
            onDismissRequest = { showEditDialog = false },
            confirmButton = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = { showEditDialog = false }) {
                        Text(text = "Cancel")
                    }
                    Button(onClick = {
                        if (itemName.isNotBlank() && itemPrice.isNotBlank() && itemStock.isNotBlank() && selectedCategory.isNotBlank()) {
                            coroutineScope.launch {
                                selectedItem?.let {
                                    val editedItem = it.copy(
                                        namaProduk = itemName,
                                        hargaProduk = itemPrice.toInt(),
                                        stokProduk = itemStock.toInt(),
                                        kategoriProduk = selectedCategory
                                    )
                                    viewModel.editItem(it.id ?: "", editedItem)
                                }
                            }
                            showEditDialog = false
                        }
                    }) {
                        Text(text = "Edit")
                    }
                }
            },
            title = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Edit Produk")
                }
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = itemName,
                        onValueChange = { itemName = it },
                        label = { Text("Name") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            capitalization = KeyboardCapitalization.Sentences
                        )
                    )
                    OutlinedTextField(
                        value = itemStock,
                        onValueChange = { itemStock = it },
                        label = { Text("Stock") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = itemPrice,
                        onValueChange = { itemPrice = it },
                        label = { Text("Price") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = {
                            expanded = !expanded
                        }
                    ) {
                        TextField(
                            value = selectedCategory,
                            onValueChange = {},
                            readOnly = true,
                            placeholder = { Text("Category") },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                            modifier = Modifier.menuAnchor()
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            categories.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item) },
                                    onClick = {
                                        selectedCategory = item
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                }
            }
        )
    }



//    if (showSuccessDialog) {
//        AlertDialog(
//            onDismissRequest = { showSuccessDialog = false },
//            confirmButton = {
//                Button(onClick = { showSuccessDialog = false }) {
//                    Text(text = "OK")
//                }
//            },
//            title = {
//                Box(
//                    modifier = Modifier.fillMaxWidth(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text("Success")
//                }
//            },
//            text = {
//                Text("Product added successfully!")
//            }
//        )
//    }
}
