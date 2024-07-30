package com.mobiledev.myshoppinglistapp.ui.NavigationDrawer

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobiledev.myshoppinglistapp.WelcomeScreen
import com.mobiledev.myshoppinglistapp.di.ShoppingListViewModelFactory
import com.mobiledev.myshoppinglistapp.ui.Produk.ProdukViewModel
import com.mobiledev.myshoppinglistapp.ui.Produk.ShoppingListApp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(viewModelFactory: ShoppingListViewModelFactory) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var selectedMenu by remember { mutableStateOf("Welcome") }
    val produkViewModel: ProdukViewModel = viewModel(factory = viewModelFactory)
//    val transaksiViewModel: TransaksiViewModel = viewModel()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { // Added TopAppBar with navigation icon to open the drawer
            TopAppBar(
                title = { Text("My Shopping List App") },
                navigationIcon = {
                    IconButton(onClick = { scope.launch { scaffoldState.drawerState.open() } }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                }
            )
        },
        drawerContent = { // Drawer content with menu items
            Box(modifier = Modifier)
            DrawerContent { menu ->
                selectedMenu = menu // Update selected menu
                scope.launch {
                    scaffoldState.drawerState.close() // Close drawer after selection
                }
            }
        }
    ) { paddingValues ->
        when (selectedMenu) {
            "Welcome" -> WelcomeScreen()
            "Produk" -> ShoppingListApp(produkViewModel)
//            "Transaksi" -> ListTransaksi(transaksiViewModel)
        }
    }
}