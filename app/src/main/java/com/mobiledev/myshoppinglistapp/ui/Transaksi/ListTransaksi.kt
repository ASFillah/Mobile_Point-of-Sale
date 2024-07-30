//package com.mobiledev.myshoppinglistapp.ui.Transaksi
//
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import com.mobiledev.myshoppinglistapp.Response.TransaksiResponse.DataItem
//
//@Composable
//fun ListTransaksi(viewModel: TransaksiViewModel) {
//    val transaksiItems by viewModel.transaksiItems.collectAsState()
//    val grandTotal by viewModel.grandTotal.collectAsState()
//    var itemName by remember { mutableStateOf("") }
//    var itemStock by remember { mutableStateOf("") }
//    var itemPrice by remember { mutableStateOf("") }
//    var selectedItem by remember { mutableStateOf <DataItem?>(null) }
//    val categories = arrayOf("Makanan", "Minuman")
//
//    val coroutineScope = rememberCoroutineScope()
//
//    Column(
////        modifier = Modifier.fillMaxSize(),
////        verticalArrangement = Arrangement.Center
//    ) {
//        LazyColumn (
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp)
//        ) {
//            items(transaksiItems) { item ->
//                TransaksiListItem(
//                    item = item,
//                    onAdd = {
//
//                    },
//                    onSubtract = {
//
//                    }
//                )
//            }
//        }
//    }
//}