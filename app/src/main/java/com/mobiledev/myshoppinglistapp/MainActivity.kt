package com.mobiledev.myshoppinglistapp

//import com.mobiledev.myshoppinglistapp.ui.Transaksi.ListTransaksi
//import com.mobiledev.myshoppinglistapp.ui.Transaksi.TransaksiViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import com.mobiledev.myshoppinglistapp.Retrofit.ApiConfig
import com.mobiledev.myshoppinglistapp.data.ProdukRepository
import com.mobiledev.myshoppinglistapp.di.ShoppingListViewModelFactory
import com.mobiledev.myshoppinglistapp.ui.NavigationDrawer.MyApp
import com.mobiledev.myshoppinglistapp.ui.Produk.ProdukViewModel
import com.mobiledev.myshoppinglistapp.ui.theme.MyShoppingListAppTheme

class MainActivity : ComponentActivity() {
    private val repository: ProdukRepository by lazy {
        ProdukRepository(ApiConfig.getApiService())
    }
    private val viewModelFactory = ShoppingListViewModelFactory(repository)
    private val viewModel: ProdukViewModel by viewModels { viewModelFactory }

//    private val transaksiRepository: TransaksiRepository by lazy {
//        TransaksiRepository(ApiConfig.getApiService())
//    }
//    private val transaksiViewModelFactory = TransaksiViewModelFactory(transaksiRepository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyShoppingListAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp(viewModelFactory)
                }
            }
        }
    }
}