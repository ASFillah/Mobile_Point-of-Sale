package com.mobiledev.myshoppinglistapp.ui.Produk

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobiledev.myshoppinglistapp.Response.DataItem
import com.mobiledev.myshoppinglistapp.Response.GetProdukResponse
import com.mobiledev.myshoppinglistapp.data.ProdukRepository
import com.mobiledev.myshoppinglistapp.data.ResultState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProdukViewModel (private val repository: ProdukRepository) : ViewModel() {
    val items: StateFlow<List<DataItem>> = repository.items

    private val _produkLiveData = MutableLiveData<ResultState<GetProdukResponse>>()
    val produkLiveData: LiveData<ResultState<GetProdukResponse>> = _produkLiveData

    init {
        getItem()
    }

    private fun getItem(){
        viewModelScope.launch {
            _produkLiveData.value = ResultState.Loading
            try {
                val response = repository.apiService.getProduk()
                if (response.error == false) {
                    response.data?.let { data ->
                        repository.setItems(data.filterNotNull())
                        _produkLiveData.value = ResultState.Success(response)
                    }
                } else {
                    _produkLiveData.value = ResultState.Error(response.message)
                }
            } catch (e: Exception){
                _produkLiveData.value = ResultState.Error(e.toString())
            }
        }
    }


    suspend fun addItem(name: String, price: Int, stock: Int, category: String) {
        val result = repository.addItem(name, price, stock, category)
        if (result is ResultState.Success) {
            getItem() // Refresh items after adding the product
        }
    }

    suspend fun editItem(id: String, produk: DataItem) {
        repository.editItem(id, produk)
        getItem()
    }

    suspend fun deleteItem(id: String) {
        repository.deleteItem(id)
        getItem()
    }
}