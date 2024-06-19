package com.mobiledev.myshoppinglistapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobiledev.myshoppinglistapp.Response.DataItem
import com.mobiledev.myshoppinglistapp.Response.EditProdukResponse
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


    fun addItem(name: String, price: Int, stock: Int) {
        viewModelScope.launch {
            repository.addItem(name, price, stock)
        }
    }

    fun editItem(updateItem: EditProdukResponse) {
        viewModelScope.launch {
            repository.editItem(updateItem)
        }
    }

    fun deleteItem(itemId: String) {
        viewModelScope.launch {
            repository.deleteItem(itemId)
        }
    }
}